'use client';

import Link from 'next/link';
import {
  CalendarDaysIcon,
  CheckIcon,
  ClockIcon,
  ExclamationCircleIcon,
} from '@heroicons/react/24/outline';
import { Button } from '@/app/ui/button';
import { createProject } from '@/app/lib/actions';
import { useFormState } from 'react-dom';

export default function Form() {
  // Must have the message and errors fields
  const initialState = { message: null, errors: {} };
  const [state, dispatch] = useFormState(createProject, initialState); // Assuming createProject function is defined elsewhere

  return (
    // <form action={dispatch} className="mx-auto max-w-lg">

    <form action={dispatch}>
      <div className="rounded-md bg-gray-50 p-4 md:p-6">
        {/* Project Name */}
        <div className="mb-4">
          <label htmlFor="name" className="mb-2 block text-sm font-medium">
            Project Name
          </label>
          <input
            id="name"
            name="name"
            type="text"
            required
            placeholder="Enter project name"
            className="block w-full rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
            aria-describedby="name-error"
          />
          {/* To show when there is an error in the name field */}
          <div id="name-error" aria-live="polite" aria-atomic="true">
            {state.errors?.name?.map((error: string) => (
              <p className="mt-2 text-sm text-red-500" key={error}>
                {error}
              </p>
            ))}
          </div>
        </div>
        {/* Project Start Date */}
        <div className="mb-4">
          <label htmlFor="startDate" className="mb-2 block text-sm font-medium">
            Start Date
          </label>
          <input
            id="startDate"
            name="startDate"
            type="date"
            className="block w-full rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
            aria-describedby="startDate-error"
          />
          {/* To show when there is an error in the start date field */}
          <div id="startDate-error" aria-live="polite" aria-atomic="true">
            {state.errors?.startDate?.map((error: string) => (
              <p className="mt-2 text-sm text-red-500" key={error}>
                {error}
              </p>
            ))}
          </div>
        </div>
        {/* Project End Date */}
        <div className="mb-4">
          <label htmlFor="duration" className="mb-2 block text-sm font-medium">
            Project Estimated Duration
          </label>
          <input
            id="duration"
            name="duration"
            required
            placeholder="Enter project duration in days"
            type="number"
            className="block w-full rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
          />
          {/* To show when there is an error in the start date field */}
          <div id="duration-error" aria-live="polite" aria-atomic="true">
            {state.errors?.duration?.map((error: string) => (
              <p className="mt-2 text-sm text-red-500" key={error}>
                {error}
              </p>
            ))}
          </div>
        </div>
        {/* Project Status */}
        <fieldset>
          <legend className="mb-2 block text-sm font-medium">
            Set the project status
          </legend>
          <div className="rounded-md border border-gray-200 bg-white px-[14px] py-3">
            <div className="flex gap-4">
              <div className="flex items-center">
                <input
                  id="planned"
                  name="status"
                  type="radio"
                  value="planned"
                  defaultChecked={true}
                  className="h-4 w-4 cursor-pointer border-gray-300 bg-gray-100 text-gray-600 focus:ring-2"
                />
                <label
                  htmlFor="planned"
                  className="ml-2 flex cursor-pointer items-center gap-1.5 rounded-full bg-gray-100 px-3 py-1.5 text-xs font-medium text-gray-600"
                >
                  Planned <CalendarDaysIcon className="h-4 w-4" />
                </label>
              </div>
              <div className="flex items-center">
                <input
                  id="ongoing"
                  name="status"
                  type="radio"
                  value="ongoing"
                  className="h-4 w-4 cursor-pointer border-gray-300 bg-gray-100 text-gray-600 focus:ring-2"
                />
                <label
                  htmlFor="ongoing"
                  className="ml-2 flex cursor-pointer items-center gap-1.5 rounded-full bg-blue-500 px-3 py-1.5 text-xs font-medium text-white"
                >
                  Ongoing
                  <ClockIcon className="h-4 w-4" />
                </label>
              </div>
              <div className="flex items-center">
                <input
                  id="completed"
                  name="status"
                  type="radio"
                  value="completed"
                  className="h-4 w-4 cursor-pointer border-gray-300 bg-gray-100 text-gray-600 focus:ring-2"
                />
                <label
                  htmlFor="completed"
                  className="ml-2 flex cursor-pointer items-center gap-1.5 rounded-full bg-green-500 px-3 py-1.5 text-xs font-medium text-white"
                >
                  Completed
                  <CheckIcon className="h-4 w-4" />
                </label>
              </div>
            </div>
            {/* Only show when there's an error with the status field */}
            <div id="status-error" aria-live="polite" aria-atomic="true">
              {state?.errors?.status?.map((error: string) => (
                <p className="mt-2 text-sm text-red-500" key={error}>
                  {error}
                </p>
              ))}
            </div>
          </div>
        </fieldset>
        {/* Project Budget */}
        <div className="mb-4">
          <label htmlFor="budget" className="mb-2 block text-sm font-medium">
            Budget
          </label>
          <input
            id="budget"
            name="budget"
            type="number"
            step="0.01"
            placeholder="Enter project budget"
            className="block w-full rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
          />
        </div>
        {/* Project Stakeholders */}
        <div className="mb-4">
          <label
            htmlFor="stakeholders"
            className="mb-2 block text-sm font-medium"
          >
            Stakeholders
          </label>
          <input
            id="stakeholders"
            name="stakeholders"
            type="text"
            placeholder="Enter project stakeholders"
            className="block w-full rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
          />
        </div>
        {/* Project Description */}
        <div className="mb-4">
          <label
            htmlFor="description"
            className="mb-2 block text-sm font-medium"
          >
            Description
          </label>
          <textarea
            id="description"
            name="description"
            placeholder="Enter project description"
            className="block w-full resize-none rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
            rows={3}
          />
        </div>
      </div>
      <div
        className="flex h-8 items-end space-x-1"
        aria-live="polite"
        aria-atomic="true"
      >
        {state.message && (
          <>
            <ExclamationCircleIcon className="h-5 w-5 text-red-500" />
            <p className="text-sm text-red-500">{state.message}</p>
          </>
        )}
      </div>
      <div className="mt-6 flex justify-end gap-4">
        <Link
          href="/dashboard/projects"
          className="flex h-10 items-center rounded-lg bg-gray-100 px-4 text-sm font-medium text-gray-600 transition-colors hover:bg-gray-200"
        >
          Cancel
        </Link>
        <Button type="submit">Create Project</Button>
      </div>
    </form>
  );
}
