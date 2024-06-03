import Form from '@/app/ui/projects/edit-form';
import Breadcrumbs from '@/app/ui/invoices/breadcrumbs';
import { notFound } from 'next/navigation';
import { fetchProjectById } from '@/app/lib/data';

export default async function Page({ params }: { params: { id: number } }) {
  const id = params.id;
  const [project] = await Promise.all([fetchProjectById(id)]);
  if (!project) {
    // this a fallback that will call the not-found.tsx file found in the path
    notFound();
  }
  return (
    <main>
      <Breadcrumbs
        breadcrumbs={[
          { label: 'Invoices', href: '/dashboard/projects' },
          {
            label: 'Edit Project',
            href: `/dashboard/projects/${id}/edit`,
            active: true,
          },
        ]}
      />
      <Form project={project} />
    </main>
  );
}
