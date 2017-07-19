

-- roller_permission constant updated to be more clear
update roller_properties set name = 'user.account.email.activation' where name = 'user.account.activation.enabled';

-- website table was renamed to weblog and several unused columns dropped
rename table website TO weblog;
alter table weblog drop column emailfromaddress;
alter table weblog drop column pagemodels;
alter table weblog drop column defaultcatid;
alter table weblog drop column ignorewords;
alter table weblog drop column defaultpageid;
alter table weblog drop column weblogdayid;

-- two weblog columns renamed
    alter table weblog add column visible smallint with default 1 not null;
    alter table weblog add column tagline varchar(255) default null;
update weblog set visible = isenabled;
update weblog set tagline = description;
alter table weblog drop column isenabled;
alter table weblog drop column description;

-- different value for our Xinha editor
update weblog set editorpage = 'editor-xinha.jsp' where editorpage = 'editor-rte.jsp';

-- some tables renamed
rename table folder TO bookmark_folder;
rename table rolleruser TO roller_user;
rename table webpage TO weblog_custom_template;

-- openID value moved from deprecated roller_userattribute table to new roller_user.openid_url column
    alter table roller_user add column openid_url varchar(255) default null;

update roller_user ru
set openid_url = (select attrvalue from roller_userattribute rua where attrname = 'openid.url' and ru.username = rua.username)
where username in (select username from roller_userattribute rua where attrname = 'openid.url');

-- roller_userattribute table no longer referenced by Roller application, can be
-- manually dropped if your project is not using it for anything else

create table custom_template_rendition (
    id varchar(48)  not null primary key,
    templateid varchar(48) not null,
    template     clob(102400) not null,
    templatelang varchar(48),
       type      varchar(16) not null default 'STANDARD'
);

-- following may not run on MySQL, recommended to try manually
-- alter table custom_template_rendition add constraint ctr_templateid_fk
--    foreign key ( templateid ) references weblog_custom_template( id )  ;

-- capitalizing column constants as these are now stored as enums in Java.
update weblog_custom_template set templatelang = upper(templatelang);
update weblog_custom_template wct set action = upper(action);

-- The main stylesheet for a theme has a new action, STYLESHEET.
update weblog_custom_template wct set action='STYLESHEET' where link is not null and link = (select customstylesheet from weblog w where w.id = wct.websiteid);

-- With above statement, weblog.customstylesheet no longer needed.
alter table weblog drop column customstylesheet;

-- template renditions now stored in custom_template_rendition table; 5.0.x has only standard (non-mobile) type
-- important: Don't run below statement if upgrading from a 5.1 snapshot to 5.1 release version as
-- custom_template_rendition already has your custom templates, it would end up overwriting with default ones
insert into custom_template_rendition(id, templateid, template, templatelang, type)
select id, id, template, templatelang, 'STANDARD' from weblog_custom_template;

-- With above statement, below columns no longer needed
alter table weblog_custom_template drop column template;
alter table weblog_custom_template drop column templatelang;
alter table weblog_custom_template drop column decorator;

-- HTML header search description now available for each blog entry
    alter table weblogentry add column search_description varchar(255) default null;

-- Removal of subcategories means no more path and parentid columns
delete from weblogcategory where name = 'root' and parentid is null;
alter table weblogcategory drop column parentid;
alter table weblogcategory drop column path;

-- Allow users to order their weblog categories (zero-based)
    alter table weblogcategory add column position integer with default 0 not null;

-- Removal of custom ping targets
delete from pingtarget where websiteid is not null;
alter table pingtarget drop foreign key pt_websiteid_fk;
alter table pingtarget drop column websiteid;

-- If you run this script manually (i.e. you are doing installation.type=manual)
-- them you may need to comment out this next statement, this index does not 
-- exist in all Roller systems:
alter table bookmark_folder drop index folder_namefolderid_uq;

-- Removal of bookmark subfolders and renaming of former root folder to 'default'
-- If a bookmark folder with name 'default' already exists, rename it by adding its id to it.
update bookmark_folder set name = 	name || id 
 where name = 'default';
update bookmark_folder set name = 'default' where name = 'root' and parentid is null;
alter table bookmark_folder drop column parentid;
alter table bookmark_folder drop column path;
alter table bookmark_folder drop column description;
alter table bookmark drop column weight;

-- Removal of media file subfolders and renaming of former root folder to 'default'
-- If a folder with name 'default' already exists, rename it by adding its id to it.
update roller_mediafiledir set name = 	name || id 
 where name = 'default';
update roller_mediafiledir set name = 'default' where name = 'root' and parentid is null;
alter table roller_mediafiledir drop column path;
alter table roller_mediafiledir drop foreign key mf_parentid_fk;
alter table roller_mediafiledir drop column parentid;

-- Adding blog-specific web analytics (e.g. Google Analytics) tracking code
    alter table weblog add column analyticscode clob(102400) default null;

-- Referer table no longer populated, retaining for older Roller instances in case
-- legacy data is desired to keep; but removing its FK relationships to other tables
alter table referer drop foreign key ref_entryid_fk;
alter table referer drop foreign key ref_websiteid_fk;
