import { UpdateSite, DeleteSite, ConsultSitesActivities } from '@/app/ui/sites/buttons';
import { formatDateToLocal, formatCurrency } from '@/app/lib/utils';
import { fetchFilteredSites } from '@/app/lib/data';
import Status from '../common/status';
import { toStatusModel } from '@/app/lib/models';
import { HomeModernIcon } from '@heroicons/react/20/solid';

export default async function SitesTable({
  query,
  currentPage,
}: {
  query: string;
  currentPage: number;
}) {
  const sites = await fetchFilteredSites(query, currentPage);

  return (
    <div className="mt-6 flow-root">
      <div className="inline-block min-w-full align-middle">
        <div className="rounded-lg bg-gray-50 p-2 md:pt-0">
          <div className="md:hidden">
            {sites?.map((site) => (
              <div
                key={site.id}
                className="mb-2 w-full rounded-md bg-white p-4"
              >
                <div className="flex items-center justify-between border-b pb-4">
                  <div>
                    <div className="mb-2 flex items-center">
                      <HomeModernIcon
                        // src={site.image_url}
                        className="mr-2 rounded-full"
                        width={28}
                        height={28}
                        // alt={`${site.name}'s profile picture`}
                      />
                      <p>{site.name}</p>
                    </div>
                  </div>
                  <Status status={toStatusModel(site.status)} />
                </div>
                <div className="flex w-full items-center justify-between pt-4">
                  <div>
                    <p className="text-xl font-medium">
                      {formatDateToLocal(site.startDate)}
                    </p>
                    <p> {formatCurrency(site.budget)}</p>
                    {/* <p>{formatDateToLocal(site.startDate)}</p> */}
                    <p>{site.duration} Days</p>
                  </div>
                  <div className="flex justify-end gap-2">
                    {/* Receives the site id */}
                    <UpdateSite id={site.id} />
                    <DeleteSite id={site.id} />
                  </div>
                </div>
              </div>
            ))}
          </div>
          <table className="hidden min-w-full text-gray-900 md:table">
            <thead className="rounded-lg text-left text-sm font-normal">
              <tr>
                <th scope="col" className="px-4 py-5 font-medium sm:pl-6">
                  Site Name
                </th>
                <th scope="col" className="px-3 py-5 font-medium">
                  Budget
                </th>
                <th scope="col" className="px-3 py-5 font-medium">
                  Start Date
                </th>
                <th scope="col" className="px-3 py-5 font-medium">
                  Siteed Duration
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
              {sites?.map((site) => (
                <tr
                  key={site.id}
                  className="w-full border-b py-3 text-sm last-of-type:border-none [&:first-child>td:first-child]:rounded-tl-lg [&:first-child>td:last-child]:rounded-tr-lg [&:last-child>td:first-child]:rounded-bl-lg [&:last-child>td:last-child]:rounded-br-lg"
                >
                  <td className="whitespace-nowrap py-3 pl-6 pr-3">
                    <div className="flex items-center gap-3">
                      <HomeModernIcon
                        // src={site.image_url}
                        className="rounded-full"
                        width={28}
                        height={28}
                        // alt={`${site.name}'s profile picture`}
                      />
                      <p>{site.name}</p>
                    </div>
                  </td>

                  <td className="whitespace-nowrap px-3 py-3">
                    {formatCurrency(site.budget)}
                  </td>
                  <td className="whitespace-nowrap px-3 py-3">
                    {formatDateToLocal(site.startDate)}
                  </td>
                  <td className="whitespace-nowrap px-3 py-3">
                    {site.duration} Days
                  </td>
                  <td className="whitespace-nowrap px-3 py-3">
                    <Status status={toStatusModel(site.status)} />
                  </td>
                  <td className="whitespace-nowrap py-3 pl-6 pr-3">
                    <div className="flex justify-end gap-3">
                      <ConsultSitesActivities id={site.id} />
                      <UpdateSite id={site.id} />
                      <DeleteSite id={site.id} />
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
