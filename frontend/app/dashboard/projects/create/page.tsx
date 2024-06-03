import Form from '@/app/ui/projects/create-form';
import Breadcrumbs from '@/app/ui/common/breadcrumbs';
import { fetchCustomers } from '@/app/lib/data';

export default async function Page() {
  // We get all the customers first

  return (
    <main>
      {/* The breadcrumps helps the user see his current location. projects/Create Invoice  */}
      <Breadcrumbs
        breadcrumbs={[
          { label: 'Projects', href: '/dashboard/projects' },
          {
            label: 'Create Project',
            href: '/dashboard/project/create',
            active: true,
          },
        ]}
      />
      <Form />
    </main>
  );
}
