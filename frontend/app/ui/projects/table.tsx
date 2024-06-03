import { UpdateProject, DeleteProject } from '@/app/ui/projects/buttons';
import { formatDateToLocal, formatCurrency } from '@/app/lib/utils';
import { fetchFilteredProjects } from '@/app/lib/data';
import Status from '../common/status';
import { toStatusModel } from '@/app/lib/models';
import { HomeModernIcon } from '@heroicons/react/20/solid';

export default async function ProjectsTable({
  query,
  currentPage,
}: {
  query: string;
  currentPage: number;
}) {
  const projects = await fetchFilteredProjects(query, currentPage);

  return (
    <div className="mt-6 flow-root">
      <div className="inline-block min-w-full align-middle">
        <div className="rounded-lg bg-gray-50 p-2 md:pt-0">
          <div className="md:hidden">
            {projects?.map((project) => (
              <div
                key={project.id}
                className="mb-2 w-full rounded-md bg-white p-4"
              >
                <div className="flex items-center justify-between border-b pb-4">
                  <div>
                    <div className="mb-2 flex items-center">
                      <HomeModernIcon
                        // src={project.image_url}
                        className="mr-2 rounded-full"
                        width={28}
                        height={28}
                        // alt={`${project.name}'s profile picture`}
                      />
                      <p>{project.name}</p>
                    </div>
                  </div>
                  <Status status={toStatusModel(project.status)} />
                </div>
                <div className="flex w-full items-center justify-between pt-4">
                  <div>
                    <p className="text-xl font-medium">
                      {formatDateToLocal(project.startDate)}
                    </p>
                    <p> {formatCurrency(project.budget)}</p>
                    {/* <p>{formatDateToLocal(project.startDate)}</p> */}
                    <p>{project.duration} Days</p>
                  </div>
                  <div className="flex justify-end gap-2">
                    {/* Receives the project id */}
                    <UpdateProject id={project.id} />
                    <DeleteProject id={project.id} />
                  </div>
                </div>
              </div>
            ))}
          </div>
          <table className="hidden min-w-full text-gray-900 md:table">
            <thead className="rounded-lg text-left text-sm font-normal">
              <tr>
                <th scope="col" className="px-4 py-5 font-medium sm:pl-6">
                  Project Name
                </th>
                <th scope="col" className="px-3 py-5 font-medium">
                  Budget
                </th>
                <th scope="col" className="px-3 py-5 font-medium">
                  Start Date
                </th>
                <th scope="col" className="px-3 py-5 font-medium">
                  Projected Duration
                </th>
                <th scope="col" className="px-3 py-5 font-medium">
                  Status
                </th>
                <th scope="col" className="relative py-3 pl-6 pr-3">
                  <span className="sr-only">Edit</span>
                </th>
              </tr>
            </thead>
            <tbody className="bg-white">
              {projects?.map((project) => (
                <tr
                  key={project.id}
                  className="w-full border-b py-3 text-sm last-of-type:border-none [&:first-child>td:first-child]:rounded-tl-lg [&:first-child>td:last-child]:rounded-tr-lg [&:last-child>td:first-child]:rounded-bl-lg [&:last-child>td:last-child]:rounded-br-lg"
                >
                  <td className="whitespace-nowrap py-3 pl-6 pr-3">
                    <div className="flex items-center gap-3">
                      <HomeModernIcon
                        // src={project.image_url}
                        className="rounded-full"
                        width={28}
                        height={28}
                        // alt={`${project.name}'s profile picture`}
                      />
                      <p>{project.name}</p>
                    </div>
                  </td>

                  <td className="whitespace-nowrap px-3 py-3">
                    {formatCurrency(project.budget)}
                  </td>
                  <td className="whitespace-nowrap px-3 py-3">
                    {formatDateToLocal(project.startDate)}
                  </td>
                  <td className="whitespace-nowrap px-3 py-3">
                    {project.duration} Days
                  </td>
                  <td className="whitespace-nowrap px-3 py-3">
                    <Status status={toStatusModel(project.status)} />
                  </td>
                  <td className="whitespace-nowrap py-3 pl-6 pr-3">
                    <div className="flex justify-end gap-3">
                      <UpdateProject id={project.id} />
                      <DeleteProject id={project.id} />
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
