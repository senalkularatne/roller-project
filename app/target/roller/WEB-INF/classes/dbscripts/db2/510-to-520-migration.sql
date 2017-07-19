




-- Adjusting precision of datetime or timestamp columns
-- Not affects for Derby

alter table pingqueueentry alter column entrytime set data type timestamp(3) not null;
alter table pingtarget alter column lastsuccess set data type timestamp(3);
alter table rag_entry alter column published set data type timestamp(3) not null;
alter table rag_entry alter column updated set data type timestamp(3);
alter table rag_subscription alter column last_updated set data type timestamp(3);
alter table roller_audit_log alter column change_time set data type timestamp(3);
alter table roller_comment alter column posttime set data type timestamp(3) not null;
alter table roller_mediafile alter column date_uploaded set data type timestamp(3) not null;
alter table roller_mediafile alter column last_updated set data type timestamp(3);
alter table roller_oauthaccessor alter column created set data type timestamp(3) not null;
alter table roller_oauthaccessor alter column updated set data type timestamp(3) not null;
alter table roller_permission alter column datecreated set data type timestamp(3) not null;
alter table roller_tasklock alter column timeacquired set data type timestamp(3);
alter table roller_tasklock alter column lastrun set data type timestamp(3);
alter table roller_user alter column datecreated set data type timestamp(3) not null;
alter table roller_weblogentrytag alter column time set data type timestamp(3) not null;
alter table roller_weblogentrytagagg alter column lastused set data type timestamp(3) not null;
alter table weblog alter column datecreated set data type timestamp(3) not null;
alter table weblog alter column lastmodified set data type timestamp(3);
alter table weblog_custom_template alter column updatetime set data type timestamp(3) not null;
alter table weblogentry alter column pubtime set data type timestamp(3);
alter table weblogentry alter column updatetime set data type timestamp(3) not null;
