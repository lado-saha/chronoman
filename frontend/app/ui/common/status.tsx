import { StatusModel } from '@/app/lib/models';
import {
  CalendarDaysIcon,
  CheckIcon,
  ClockIcon,
} from '@heroicons/react/24/outline';
import clsx from 'clsx';

export default function Status({ status }: { status: StatusModel }) {
  return (
    <span
      className={clsx(
        'inline-flex items-center rounded-full px-2 py-1 text-xs',
        {
          'bg-gray-100 text-gray-500': status === StatusModel.Planned,
          'bg-yellow-500 text-gray-500': status === StatusModel.Ongoing,
          'bg-green-500 text-white': status === StatusModel.Completed,
        },
      )}
    >
      {status === StatusModel.Planned ? (
        <>
          Planned
          <CalendarDaysIcon className="ml-1 w-4 text-gray-500" />
        </>
      ) : null}
      {status === StatusModel.Ongoing ? (
        <>
          Ongoing
          <ClockIcon className="ml-1 w-4 text-gray-500" />
        </>
      ) : null}
      {status === StatusModel.Completed ? (
        <>
          Completed
          <CheckIcon className="ml-1 w-4 text-white" />
        </>
      ) : null}
    </span>
  );
}
