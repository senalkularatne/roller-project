




-- Adjusting precision of datetime or timestamp columns
-- Not affects for Derby

alter table pingqueueentry alter column entrytime datetime(3) not null;
alter table pingtarget alter column lastsuccess datetime(3);
alter table rag_entry alter column published datetime(3) not null;
alter table rag_entry alter column updated datetime(3);
alter table rag_subscription alter column last_updated datetime(3);
alter table roller_audit_log alter column change_time datetime(3);
alter table roller_comment alter column posttime datetime(3) not null;
alter table roller_mediafile alter column date_uploaded datetime(3) not null;
alter table roller_mediafile alter column last_updated datetime(3);
alter table roller_oauthaccessor alter column created datetime(3) not null;
alter table roller_oauthaccessor alter column updated datetime(3) not null;
alter table roller_permission alter column datecreated datetime(3) not null;
alter table roller_tasklock alter column timeacquired datetime(3);
alter table roller_tasklock alter column lastrun datetime(3);
alter table roller_user alter column datecreated datetime(3) not null;
alter table roller_weblogentrytag alter column time datetime(3) not null;
alter table roller_weblogentrytagagg alter column lastused datetime(3) not null;
alter table weblog alter column datecreated datetime(3) not null;
alter table weblog alter column lastmodified datetime(3);
alter table weblog_custom_template alter column updatetime datetime(3) not null;
alter table weblogentry alter column pubtime datetime(3);
alter table weblogentry alter column updatetime datetime(3) not null;
