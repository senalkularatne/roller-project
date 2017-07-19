




-- Adjusting precision of datetime or timestamp columns
-- Not affects for Derby

alter table pingqueueentry alter column entrytime timestamp(3) not null;
alter table pingtarget alter column lastsuccess timestamp(3);
alter table rag_entry alter column published timestamp(3) not null;
alter table rag_entry alter column updated timestamp(3);
alter table rag_subscription alter column last_updated timestamp(3);
alter table roller_audit_log alter column change_time timestamp(3);
alter table roller_comment alter column posttime timestamp(3) not null;
alter table roller_mediafile alter column date_uploaded timestamp(3) not null;
alter table roller_mediafile alter column last_updated timestamp(3);
alter table roller_oauthaccessor alter column created timestamp(3) not null;
alter table roller_oauthaccessor alter column updated timestamp(3) not null;
alter table roller_permission alter column datecreated timestamp(3) not null;
alter table roller_tasklock alter column timeacquired timestamp(3);
alter table roller_tasklock alter column lastrun timestamp(3);
alter table roller_user alter column datecreated timestamp(3) not null;
alter table roller_weblogentrytag alter column time timestamp(3) not null;
alter table roller_weblogentrytagagg alter column lastused timestamp(3) not null;
alter table weblog alter column datecreated timestamp(3) not null;
alter table weblog alter column lastmodified timestamp(3);
alter table weblog_custom_template alter column updatetime timestamp(3) not null;
alter table weblogentry alter column pubtime timestamp(3);
alter table weblogentry alter column updatetime timestamp(3) not null;
