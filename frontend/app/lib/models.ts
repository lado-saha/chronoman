// models.ts

// Type alias for Project
export type Project = {
  id: number;
  name: string;
  description?: string;
  startDate: string; // Use string for dates to easily parse them with Date constructor
  endDate?: string;
  status: string;
  budget?: number;
  stakeholders?: string;
  createdAt: string;
  updatedAt: string;
};

// Type alias for Location
export type Location = {
  id: number;
  name: string;
  latitude: number;
  longitude: number;
  region: string;
  town: string;
  country: string;
  createdAt: string;
  updatedAt: string;
};

// Type alias for Site
export type Site = {
  id: number;
  projectId: number;
  locationId: number;
  name: string;
  startDate: string;
  estimationDuration: number;
  status: string;
  createdAt: string;
  updatedAt: string;
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
;