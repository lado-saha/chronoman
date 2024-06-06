import Pagination from '@/app/ui/common/pagination';
import Search from '@/app/ui/search';
import Table from '@/app/ui/sites/table';
import { CreateSite } from '@/app/ui/sites/buttons';
import { lusitana } from '@/app/ui/fonts';
import { Suspense } from 'react';
import { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'Activity & Tasks',
};
export default async function Page({
  searchParams,
}: {
  searchParams?: {
    query?: string;
    page?: string;
  };
}) {
  const query = searchParams?.query || '';
  const currentPage = 1;
  const totalPages = 1;

  return (
    <div className="w-full">
      <div className="flex w-full items-center justify-between">
        <h1 className={`${lusitana.className} text-2xl`}>Activities</h1>
      </div>
      <div className="mt-4 flex items-center justify-between gap-2 md:mt-8">
        <Search placeholder="Search Activity or Task..." />
        {/* <CreateSite /> */}
      </div>

      {/* Shows the sites loading */}
      <Suspense key={query + currentPage}>
        <Table query={query} currentPage={currentPage} />
      </Suspense>

      <div className="mt-5 flex w-full justify-center">
        <Pagination totalPages={totalPages} />
      </div>
    </div>
  );
}
