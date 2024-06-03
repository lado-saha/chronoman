'use client';

import {
  CalendarDaysIcon,
  CheckIcon,
  ClockIcon,
  CurrencyDollarIcon,
} from '@heroicons/react/24/outline';
import Link from 'next/link';
import { Button } from '@/app/ui/button';
import { useFormState } from 'react-dom';
import { Project } from '@/app/lib/models';
import { updateProject } from '@/app/lib/actions';

export default function EditProjectForm({ project }: { project: Project }) {
  // This will automatically bind the id to the path
  const updateProjectWithId = updateProject.bind(null, project.id);

  const initialState = { message: null, errors: {} };
  const [state, dispatch] = useFormState(updateProjectWithId, initialState);

  /*This cannot work: <form action={updateProject(id)}></form> */
  return (
    <form action={dispatch}>
      {/* Project Name */}
      <div className="mb-4">
        <label htmlFor="name" className="mb-2 block text-sm font-medium">
          Project Name
        </label>
        <div className="relative mt-2 rounded-md">
          <div className="relative">
            <input
              id="name"
              name="name"
              type="text"
              defaultValue={project.name}
              placeholder="Enter project name"
              className="peer block w-full rounded-md border border-gray-200 py-2 pl-4 text-sm outline-2 placeholder:text-gray-500"
            />
          </div>
        </div>
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
          defaultValue={Date.parse(project.startDate)}
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

      <div className="mb-4">
        <label htmlFor="duration" className="mb-2 block text-sm font-medium">
          Project Estimated Duration
        </label>
        <input
          id="duration"
          name="duration"
          type="number"
          placeholder="Enter project duration in days"
          defaultValue={project.duration}
          className="block w-full rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
        />
        <div id="endDate-error" aria-live="polite" aria-atomic="true">
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
                defaultChecked={project.status === 'planned'}
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
                defaultChecked={project.status === 'ongoing'}
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
                defaultChecked={project.status === 'completed'}
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
          defaultValue={project.stakeholders}
          placeholder="Enter project stakeholders"
          className="block w-full rounded-md border border-gray-200 px-3 py-2 text-sm placeholder-gray-500 outline-none"
        />
      </div>

      {/* Project Budget */}
      <div className="mb-4">
        <label htmlFor="budget" className="mb-2 block text-sm font-medium">
          Project Budget
        </label>
        <div className="relative mt-2 rounded-md">
          <div className="relative">
            <input
              id="budget"
              name="budget"
              type="number"
              step="0.01"
              defaultValue={project.budget}
              placeholder="Enter project budget"
              className="peer block w-full rounded-md border border-gray-200 py-2 pl-4 text-sm outline-2 placeholder:text-gray-500"
            />
          </div>
        </div>
        {/* To show when there is an error in the budget field */}
      </div>
      {/* Project Description */}
      <div className="mb-4">
        <label htmlFor="description" className="mb-2 block text-sm font-medium">
          Project Description
        </label>
        <div className="relative mt-2 rounded-md">
          <div className="relative">
            <textarea
              id="description"
              name="description"
              defaultValue={project.description}
              placeholder="Enter project description"
              className="peer block w-full rounded-md border border-gray-200 py-2 pl-4 text-sm outline-2 placeholder:text-gray-500"
            />
          </div>
        </div>
      </div>

      <div className="mt-6 flex justify-end gap-4">
        <Link
          href="/dashboard/projects"
          className="flex h-10 items-center rounded-lg bg-gray-100 px-4 text-sm font-medium text-gray-600 transition-colors hover:bg-gray-200"
        >
          Cancel
        </Link>
        <Button type="submit">Edit Project</Button>
      </div>
    </form>
  );
}
