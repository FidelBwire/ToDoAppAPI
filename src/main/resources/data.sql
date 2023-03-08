DROP TABLE IF EXISTS projects_View;
CREATE OR REPLACE VIEW projects_view AS
SELECT p.`id`, `description`, `scheduled_completion_date`, `scheduled_start_date`, 
		`title`, `username` AS created_by, `created_at` FROM `projects` p 
LEFT JOIN (
     SELECT `id`, `username` FROM `users`
) u ON p.created_by_id = u.`id`;

DROP TABLE IF EXISTS tasks_view;
CREATE OR REPLACE VIEW tasks_view AS
SELECT t.`id`, `created_on`, `description`, `scheduled_completion_date`, `scheduled_start_date`, 
	`title`, t.`updated_on`, (SELECT `username` FROM `users` WHERE `id`=t.`updated_by_id`) AS updated_by, 
	t.`updated_by_id`, (SELECT `username` FROM `users` WHERE `id`=`created_by_id`) AS created_by, 
	`created_by_id`, (SELECT `title` FROM `projects` WHERE `id`=`project_id`) AS `project_title`, 
	`project_id`, r.`id` AS report_id, `content`, `posted_on` AS report_submission_date, `task_status`, r.`updated_on` AS report_update_date FROM `tasks` t
LEFT JOIN (
    SELECT `id`, `content`, `posted_on`, `task_status`, `updated_on`, `posted_by_id`, `task_id`, `updated_by_id` FROM `reports`
) r ON t.id = r.`task_id`;

DROP TABLE IF EXISTS reports_view;
CREATE OR REPLACE VIEW reports_view AS
SELECT `id`, `content`, `posted_on` AS report_submission_date, `task_status`, `updated_on` AS report_update_date 
	FROM `reports`;