// models.ts

// Type alias for site

export type Site = {
  id: number;
  name: string;
  startDate: string;
  status: string;
  createdAt?: string;
  updatedAt?: string;
  description?: string;
  duration: number;
  stakeholders?: string;
  budget: number;
  latitude: number;
  longitude: number;
  region: string;
  town: string;
  country: string;
};

// Type alias for PredefinedActivity
export type PredefinedActivity = {
  id: number;
  name: string;
  description?: string;
};

// Type alias for PredefinedTask
export type PredefinedTask = {
  id: number;
  activityId: number;
  name: string;
  description?: string;
};

// Type alias for Activity
export type Activity = {
  id: number;
  predefinedActivityId: number;
  siteId: number;
  status: string;
  duration: number;
  comment?: string;
  startDate: string;
  realEndDate?: string;
  createdAt: string;
  updatedAt: string;
};

// Type alias for Task
export type Task = {
  id: number;
  activityId: number;
  predefinedTaskId: number;
  status: string;
  duration: number;
  comment?: string;
  startDate: string;
  realEndDate?: string;
  createdAt: string;
  updatedAt: string;
};

// Type alias for User
export type User = {
  id: string;
  password: string;
  name: string;
  role: string;
  email: string;
  createdAt?: string;
  updatedAt?: string;
  lastLogin?: string;
};

export enum StatusModel {
  Planned = 'Planned',
  Ongoing = 'Ongoing',
  Completed = 'Completed',
}

export function toStatusModel(status: string): StatusModel {
  switch (status.toLowerCase()) {
    case 'planned':
      return StatusModel.Planned;
    case 'ongoing':
      return StatusModel.Ongoing;
    case 'completed':
      return StatusModel.Completed;
    default:
      throw new Error(`Unknown status: ${status}`);
  }
}
