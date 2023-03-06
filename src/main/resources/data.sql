DROP TABLE IF EXISTS projects_View;
CREATE OR REPLACE VIEW projects_view AS
SELECT p.`id`, `description`, `scheduled_completion_date`, `scheduled_start_date`, 
		`title`, `username` AS created_by, `created_at` FROM `projects` p 
LEFT JOIN (
     SELECT `id`, `username` FROM `users`
) u ON p.created_by_id = u.`id`;

DROP TABLE IF EXISTS tasks_view;
CREATE OR REPLACE VIEW tasks_view AS
SELECT `id`, `created_on`, `description`, `scheduled_completion_date`, `scheduled_start_date`, 
	`title`, `updated_on`, (SELECT `username` FROM `users` WHERE `id`=`updated_by_id`) AS updated_by, 
	`updated_by_id`, (SELECT `username` FROM `users` WHERE `id`=`created_by_id`) AS created_by, 
	`created_by_id`, (SELECT `title` FROM `projects` WHERE `id`=`project_id`) AS `project_title`, 
	`project_id` FROM `tasks`;