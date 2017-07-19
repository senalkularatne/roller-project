




-- Adjusting precision of datetime or timestamp columns
-- Not affects for Derby

alter table pingqueueentry modify entrytime timestamp(3) not null;
alter table pingtarget modify lastsuccess timestamp(3);
alter table rag_entry modify published timestamp(3) not null;
alter table rag_entry modify updated timestamp(3);
alter table rag_subscription modify last_updated timestamp(3);
alter table roller_audit_log modify change_time timestamp(3);
alter table roller_comment modify posttime timestamp(3) not null;
alter table roller_mediafile modify date_uploaded timestamp(3) not null;
alter table roller_mediafile modify last_updated timestamp(3);
alter table roller_oauthaccessor modify created timestamp(3) not null;
alter table roller_oauthaccessor modify updated timestamp(3) not null;
alter table roller_permission modify datecreated timestamp(3) not null;
alter table roller_tasklock modify timeacquired timestamp(3);
alter table roller_tasklock modify lastrun timestamp(3);
alter table roller_user modify datecreated timestamp(3) not null;
alter table roller_weblogentrytag modify time timestamp(3) not null;
alter table roller_weblogentrytagagg modify lastused timestamp(3) not null;
alter table weblog modify datecreated timestamp(3) not null;
alter table weblog modify lastmodified timestamp(3);
alter table weblog_custom_template modify updatetime timestamp(3) not null;
alter table weblogentry modify pubtime timestamp(3);
alter table weblogentry modify updatetime timestamp(3) not null;
