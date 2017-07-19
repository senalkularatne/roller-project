




-- Adjusting precision of datetime or timestamp columns
-- Not affects for Derby

alter table pingqueueentry modify entrytime datetime(3) not null;
alter table pingtarget modify lastsuccess datetime(3);
alter table rag_entry modify published datetime(3) not null;
alter table rag_entry modify updated datetime(3);
alter table rag_subscription modify last_updated datetime(3);
alter table roller_audit_log modify change_time datetime(3);
alter table roller_comment modify posttime datetime(3) not null;
alter table roller_mediafile modify date_uploaded datetime(3) not null;
alter table roller_mediafile modify last_updated datetime(3);
alter table roller_oauthaccessor modify created datetime(3) not null;
alter table roller_oauthaccessor modify updated datetime(3) not null;
alter table roller_permission modify datecreated datetime(3) not null;
alter table roller_tasklock modify timeacquired datetime(3);
alter table roller_tasklock modify lastrun datetime(3);
alter table roller_user modify datecreated datetime(3) not null;
alter table roller_weblogentrytag modify time datetime(3) not null;
alter table roller_weblogentrytagagg modify lastused datetime(3) not null;
alter table weblog modify datecreated datetime(3) not null;
alter table weblog modify lastmodified datetime(3);
alter table weblog_custom_template modify updatetime datetime(3) not null;
alter table weblogentry modify pubtime datetime(3);
alter table weblogentry modify updatetime datetime(3) not null;
