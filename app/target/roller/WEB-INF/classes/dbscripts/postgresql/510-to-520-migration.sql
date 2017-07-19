




-- Adjusting precision of datetime or timestamp columns
-- Not affects for Derby

alter table pingqueueentry alter column entrytime type timestamp(3) with time zone;
alter table pingtarget alter column lastsuccess type timestamp(3) with time zone;
alter table rag_entry alter column published type timestamp(3) with time zone;
alter table rag_entry alter column updated type timestamp(3) with time zone;
alter table rag_subscription alter column last_updated type timestamp(3) with time zone;
alter table roller_audit_log alter column change_time type timestamp(3) with time zone;
alter table roller_comment alter column posttime type timestamp(3) with time zone;
alter table roller_mediafile alter column date_uploaded type timestamp(3) with time zone;
alter table roller_mediafile alter column last_updated type timestamp(3) with time zone;
alter table roller_oauthaccessor alter column created type timestamp(3) with time zone;
alter table roller_oauthaccessor alter column updated type timestamp(3) with time zone;
alter table roller_permission alter column datecreated type timestamp(3) with time zone;
alter table roller_tasklock alter column timeacquired type timestamp(3) with time zone;
alter table roller_tasklock alter column lastrun type timestamp(3) with time zone;
alter table roller_user alter column datecreated type timestamp(3) with time zone;
alter table roller_weblogentrytag alter column time type timestamp(3) with time zone;
alter table roller_weblogentrytagagg alter column lastused type timestamp(3) with time zone;
alter table weblog alter column datecreated type timestamp(3) with time zone;
alter table weblog alter column lastmodified type timestamp(3) with time zone;
alter table weblog_custom_template alter column updatetime type timestamp(3) with time zone;
alter table weblogentry alter column pubtime type timestamp(3) with time zone;
alter table weblogentry alter column updatetime type timestamp(3) with time zone;
