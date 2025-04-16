ALTER TABLE restaurant DROP COLUMN work_time_closed;
ALTER TABLE restaurant DROP COLUMN work_time_open;

ALTER TABLE restaurant ADD COLUMN work_time_open TIME;
ALTER TABLE restaurant ADD COLUMN work_time_closed TIME;