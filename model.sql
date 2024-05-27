-- 1- Project
CREATE TABLE projects (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE,
    status VARCHAR(50) NOT NULL,
    budget DECIMAL(15, 2),
    status VARCHAR(50) NOT NULL,                      -- Current status of the construction site (e.g., Planned, Ongoing, Completed)
    stakeholders VARCHAR(255), -- A list of stakeholders 
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2- User
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);

--4- Location
-- Create the 'locations' table
CREATE TABLE locations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,  -- Unique identifier for each location
    name VARCHAR(255) NOT NULL,            -- Name of the location
    latitude DECIMAL(10, 8) NOT NULL,      -- Latitude of the location
    longitude DECIMAL(11, 8) NOT NULL,     -- Longitude of the location
    region VARCHAR(255) NOT NULL,          -- Region of the location
    town VARCHAR(255) NOT NULL,            -- Town of the location
    country VARCHAR(255) NOT NULL,         -- Country of the location
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

);


-- 3- Site 
-- Create the 'sites' table
CREATE TABLE sites (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,             -- Unique identifier for each construction site
    project_id BIGINT NOT NULL,                       -- Identifier of the associated project
    location_id BIGINT NOT NULL,                      -- Identifier of the associated location
    name VARCHAR(255) NOT NULL,                       -- Name of the construction site
    start_date DATE NOT NULL,                         -- Start date of the site operations
    estimation_duration INT NOT NULL,                 -- Estimated duration of the site operations in days
    status VARCHAR(50) NOT NULL,                      -- Current status of the construction site (e.g., Planned, Ongoing, Completed)
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,          -- Timestamp of when the site record was created
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- Timestamp of the last update to the site record

    FOREIGN KEY (project_id) REFERENCES projects(id), -- Foreign key reference to the 'projects' table
    FOREIGN KEY (location_id) REFERENCES locations(id) -- Foreign key reference to the 'locations' table
);


-- Create the 'predefined_activities' table
CREATE TABLE predefined_activities (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,   -- Unique identifier for each predefined activity
    name VARCHAR(255) NOT NULL,             -- Name of the predefined activity
    description TEXT                        -- Description of the predefined activity
);

-- Create the 'predefined_tasks' table
CREATE TABLE predefined_tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,   -- Unique identifier for each predefined task
    activity_id BIGINT NOT NULL,            -- Identifier of the associated predefined activity
    name VARCHAR(255) NOT NULL,             -- Name of the predefined task
    description TEXT,                       -- Description of the predefined task
    FOREIGN KEY (activity_id) REFERENCES predefined_activities(id) -- Foreign key reference to the 'predefined_activities' table
);
-- Create the 'activities' table
CREATE TABLE activities (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,                    -- Unique identifier for each activity
    predefined_activity_id BIGINT NOT NULL,                  -- Identifier of the associated predefined activity
    site_id BIGINT NOT NULL,                                 -- Identifier of the associated construction site
    status VARCHAR(50) NOT NULL,                             -- Current status of the activity
    duration INT NOT NULL,                                   -- Duration of the activity in days
    comment TEXT,                                            -- Comment for the activity
    start_date DATE NOT NULL,                                -- Start date of the activity
    real_end_date DATE,                                     -- Real end date of the activity
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the activity record was created
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Timestamp of the last update to the activity record
    FOREIGN KEY (predefined_activity_id) REFERENCES predefined_activities(id), -- Foreign key reference to the 'predefined_activities' table
    FOREIGN KEY (site_id) REFERENCES sites(id)               -- Foreign key reference to the 'sites' table
);

-- Create the 'tasks' table
CREATE TABLE tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,                    -- Unique identifier for each task
    activity_id BIGINT NOT NULL,                             -- Identifier of the associated activity
    predefined_task_id BIGINT NOT NULL,                       -- Identifier of the associated predefined task
    status VARCHAR(50) NOT NULL,                             -- Current status of the task
    duration INT NOT NULL,                                   -- Duration of the task in days
    comment TEXT,                                            -- Comment for the task
    start_date DATE NOT NULL,                                -- Start date of the task
    real_end_date DATE,                                     -- Real end date of the task
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the task record was created
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Timestamp of the last update to the task record
    FOREIGN KEY (activity_id) REFERENCES activities(id),     -- Foreign key reference to the 'activities' table
    FOREIGN KEY (predefined_task_id) REFERENCES predefined_tasks(id) -- Foreign key reference to the 'predefined_tasks' table
);