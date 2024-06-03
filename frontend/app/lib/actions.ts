// This makes sure all functions exported from here are marekd as server functions
'use server';
import { signIn } from '@/auth';
import { sql } from '@vercel/postgres';
import { AuthError } from 'next-auth';
import { revalidatePath } from 'next/cache';
import { redirect } from 'next/navigation';
// We use zod for data validation
import { z } from 'zod';

import bcrypt from 'bcryptjs';
import { Project, User } from './models';

const BASE_URL = process.env.API_BASE_URL;
// We create a form schema taking into accoun the possible errors and warning
// Great for data validation
const FormSchema = z.object({
  id: z.string(),
  customerId: z.string({
    invalid_type_error: 'Please select a customer.',
  }),
  amount: z.coerce
    .number()
    .gt(0, { message: 'Please enter an amount greater than $0.' }),
  status: z.enum(['pending', 'paid'], {
    invalid_type_error: 'Please select an invoice status.',
  }),
  date: z.string(),
});

// UserCreationFormSchema
const UserCreationFormSchema = z.object({
  id: z.string(),
  password: z.string().min(8, {
    message: 'Password must be at least 8 characters long.',
  }),
  name: z.string({
    invalid_type_error: 'Please enter a name.',
  }),
  role: z.string({
    invalid_type_error: 'Please enter your profession or role.',
  }),
  email: z.string().email({
    message: 'Please enter a valid email address.',
  }),
});

const ProjectFormSchema = z.object({
  name: z.string().max(255, 'Name must be at most 255 characters long.'),
  description: z.string().optional(), // TEXT can be optional or empty
  startDate: z.string().refine((date) => !isNaN(Date.parse(date)), {
    message: 'Invalid start date format. Use a valid date string.',
  }),
  budget: z.coerce
    .number()
    .gt(0, { message: 'Please enter an amount greater than $0.' })
    .optional(),
  duration: z.coerce
    .number()
    .gte(1, { message: 'Please enter a duration greater than 1.' }),
  status: z.string().max(50, 'Status must be at most 50 characters long.'),
  stakeholders: z
    .string()
    .max(255, 'Stakeholders must be at most 255 characters long.'),
});

// This is temporary until @types/react-dom is updated
export type State = {
  errors?: {
    customerId?: string[];
    amount?: string[];
    status?: string[];
  };
  message?: string | null;
};

export type UserState = {
  errors?: {
    name?: string[];
    id?: string[];
    role?: string[];
    password?: string[];
    email?: string[];
  };
  message?: string | null;
};

export type ProjectState = {
  errors?: {
    name?: string[];
    id?: string[];
    startDate?: string[];
    duration?: string[];
    status?: string[];
    email?: string[];
    stakeholders?: string[];
  };
  message?: string | null;
};

// Validators
const CreateUser = UserCreationFormSchema.omit({ id: true });
const CreateProject = ProjectFormSchema;
const CreateInvoice = FormSchema.omit({ id: true, date: true });
const UpdateInvoice = FormSchema.omit({ id: true, date: true });

/**
 * Notice that formData is passed automatically by setting this to the action attribute of a form
 * @param formData
 */
export async function createInvoice(prevState: State, formData: FormData) {
  // Validate and returns the 3 fields else fails
  const validatedFields = CreateInvoice.safeParse({
    customerId: formData.get('customerId'),
    amount: formData.get('amount'),
    status: formData.get('status'),
  });

  // If form validation fails, return errors early. Otherwise, continue.
  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors,
      message: 'Missing Fields. Failed to Create Invoice.',
    };
  }
  // Prepare data for insertion into the database
  const { customerId, amount, status } = validatedFields.data;
  // Stores the amount in cents
  const amountInCents = amount * 100;
  const date = new Date().toISOString().split('T')[0];

  try {
    await sql`
    INSERT INTO invoices (customer_id, amount, status, date)
    VALUES (${customerId}, ${amountInCents}, ${status}, ${date})
  `;
  } catch (error) {
    return {
      message: 'Database Error: Failed to Update Invoice.',
    };
  }
  // Test it out
  // TO avoid back navigation after the form has been created
  revalidatePath('/dashboard/invoices');
  // Redirect the user
  redirect('/dashboard/invoices');
}

export async function updateInvoice(
  id: string,
  state: State,
  formData: FormData,
) {
  const validatedFields = UpdateInvoice.safeParse({
    customerId: formData.get('customerId'),
    amount: formData.get('amount'),
    status: formData.get('status'),
  });

  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors,
      message: 'Missing Fields. Failed to Update Invoice.',
    };
  }
  const { customerId, amount, status } = validatedFields.data;
  const amountInCents = amount * 100;

  try {
    await sql`
    UPDATE invoices
    SET customer_id = ${customerId}, amount = ${amountInCents}, status = ${status}
    WHERE id = ${id}
  `;
  } catch (error) {
    return {
      message: 'Database Error: Failed to Update Invoice.',
    };
  }
  revalidatePath('/dashboard/invoices');
  // Note that redirect throws an error inorder to function and so we shoulnot use it inside a try catch block
  redirect('/dashboard/invoices');
}

export async function deleteInvoice(id: string) {
  try {
    await sql`DELETE FROM invoices WHERE id = ${id}`;
    revalidatePath('/dashboard/invoices');
  } catch (error) {
    console.error(error);
  }
}

export async function signinUser(
  prevState: string | undefined,
  formData: FormData,
) {
  try {
    await signIn('credentials', formData);
  } catch (error) {
    if (error instanceof AuthError) {
      switch (error.type) {
        case 'CredentialsSignin':
          return 'Invalid credentials.';
        default:
          return 'Something went wrong. User may not exist';
      }
    } else {
      console.log(error);
    }
    throw error;
  }
  revalidatePath('/login');
  redirect('/dashboard');
}

export async function signupUser(prevState: UserState, formData: FormData) {
  // We create the user the we navigate to the login
  const validatedFields = CreateUser.safeParse({
    name: formData.get('name'),
    role: formData.get('role'),
    email: formData.get('email'),
    password: formData.get('password'),
  });
  // console.log(`Validation=${validatedFields.}`)

  // If form validation fails, return errors early. Otherwise, continue.
  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors,
      message: 'Missing Fields. Failed to Create user.',
    };
  }
  console.log(validatedFields.data);
  const data = validatedFields.data;
  const hashedPassword = await bcrypt.hash(data.password, 10);

  const newUser: User = {
    id: '',
    password: hashedPassword,
    name: data.name,
    role: data.role,
    email: data.email,
  };
  console.log(newUser);

  try {
    const response = await fetch(`${BASE_URL}/users`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newUser),
    });

    if (!response.ok) {
      throw new Error('Failed to create user');
    }

    const createdUser = await response.json();
    console.log(`user created:${createdUser} `);
  } catch (error) {
    console.error(error);
    return { message: 'Error creating user' };
  }
  revalidatePath('/signup');
  // Note that redirect throws an error inorder to function and so we shoulnot use it inside a try catch block
  redirect('/login');
}

export async function createProject(
  prevState: ProjectState,
  formData: FormData,
) {
  // We create the user the we navigate to the login
  const validatedFields = ProjectFormSchema.safeParse({
    name: formData.get('name'),
    description: formData.get('description'),
    startDate: formData.get('startDate'),
    duration: formData.get('duration'),
    status: formData.get('status'),
    budget: formData.get('budget'),
    stakeholders: formData.get('stakeholders'),
  });

  // If form validation fails, return errors early. Otherwise, continue.
  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors,
      message: 'Missing Fields. Failed to Project.',
    };
  }

  const project: Project = {
    id: 0, // or a proper id if available
    ...validatedFields.data,
    createdAt: new Date().toISOString(),
    updatedAt: new Date().toISOString(),
  };

  console.log(project);

  try {
    const response = await fetch(`${BASE_URL}/projects`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(project),
    });

    if (!response.ok) {
      throw new Error('Failed to create project');
    }

    const createdProject = await response.json();
    console.log(`project created:${createdProject} `);
  } catch (error) {
    console.error(error);
    return { message: 'Error creating project' };
  }
  revalidatePath('/dashboard/projects');
  // Note that redirect throws an error inorder to function and so we shoulnot use it inside a try catch block
  redirect('/dashboard/projects');
}
export async function updateProject(
  id: number,
  prevState: ProjectState,
  formData: FormData,
) {
  // We create the user the we navigate to the login
  const validatedFields = ProjectFormSchema.safeParse({
    name: formData.get('name'),
    description: formData.get('description'),
    startDate: formData.get('startDate'),
    duration: formData.get('duration'),
    status: formData.get('status'),
    budget: formData.get('budget'),
    stakeholders: formData.get('stakeholders'),
  });

  // If form validation fails, return errors early. Otherwise, continue.
  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors,
      message: 'Missing Fields. Failed to Create user.',
    };
  }

  const project: Project = {
    id: 0, // or a proper id if available
    ...validatedFields.data,
    createdAt: new Date().toISOString(),
    updatedAt: new Date().toISOString(),
  };

  console.log(project);

  try {
    const response = await fetch(`${BASE_URL}/projects/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(project),
    });

    if (!response.ok) {
      throw new Error('Failed to update project');
    }

    const createdProject = await response.json();
    console.log(`project updated:${createdProject} `);
  } catch (error) {
    console.error(error);
    return { message: 'Error updating project' };
  }
  revalidatePath('/dashboard/projects');
  // Note that redirect throws an error inorder to function and so we shoulnot use it inside a try catch block
  redirect('/dashboard/projects');
}
