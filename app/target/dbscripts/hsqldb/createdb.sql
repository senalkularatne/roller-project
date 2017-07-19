

-- Run this script to create the Roller database tables in your database.

-- *****************************************************
-- Create the tables and indices

create table roller_user (
    id              varchar(48) not null primary key,
    username        varchar(255) not null,
    passphrase      varchar(255) not null,
    openid_url      varchar(255),
    screenname      varchar(255) not null,
    fullname        varchar(255) not null,
    emailaddress    varchar(255) not null,
    activationcode	varchar(48),
    datecreated     timestamp(3) not null,
    locale          varchar(20),  
    timezone        varchar(50),    
    isenabled       bit default 1 not null
);
alter table roller_user add constraint ru_username_uq unique ( username );

create table userrole (
    id               varchar(48) not null primary key,
    rolename         varchar(255) not null,
    username         varchar(255) not null
);
create index ur_username_idx on userrole( username );

-- actions: comma separated list of actions permitted by permission
-- objectid: for now this will always store weblogid
-- objectType: for now this will always be 'Weblog'
create table roller_permission (
   id              varchar(48) not null primary key,
   username        varchar(255) not null,
   actions         varchar(255), 
   objectid        varchar(48),           
   objecttype      varchar(255), 
   pending         bit default 1,         
   datecreated     timestamp(3) not null
);

-- Audit log records time and comment about change
-- user_id: user that made change
-- object_id: id of associated object, if any
-- object_class: name of associated object class (e.g. WeblogEntryData)
-- comment: description of change
-- change_time: time that change was made
create table roller_audit_log (
    id              varchar(48) not null primary key,
    user_id         varchar(48) not null,  
    object_id       varchar(48),           
    object_class    varchar(255),          
    comment_text    varchar(255) not null, 
    change_time     timestamp(3)              
);

create table weblog (
    id                varchar(48) not null primary key,
    name              varchar(255) not null,
    handle            varchar(255) not null,
    tagline           varchar(255),
    creator           varchar(255),
    enablebloggerapi  bit default 0 not null,
    editorpage        varchar(255),
    bloggercatid      varchar(48),
    allowcomments     bit default 1 not null,
    emailcomments     bit default 0 not null,
    emailaddress      varchar(255) not null,
    editortheme       varchar(255),
    locale            varchar(20),
    timezone          varchar(50),
    defaultplugins    varchar(255),
    visible           bit default 1 not null,
    isactive          bit default 1 not null,
    datecreated          timestamp(3) not null,
    blacklist            longvarchar,
    defaultallowcomments bit default 1 not null,
    defaultcommentdays   integer default 7 not null,
    commentmod           bit default 0 not null,
    displaycnt           integer default 15 not null,
    lastmodified         timestamp(3),
    enablemultilang   bit default 0 not null,
    showalllangs      bit default 1 not null,
    about             varchar(255),
    icon              varchar(255),
    analyticscode      longvarchar
);
create index ws_visible_idx on weblog(visible);
alter table weblog add constraint ws_handle_uq unique (handle);

create table weblog_custom_template (
    id              varchar(48)  not null primary key,
    name            varchar(255)  not null,
    description     varchar(255),
    link            varchar(255),
    websiteid       varchar(48) not null,
    updatetime      timestamp(3) not null,
    hidden          bit default 0 not null,
    navbar          bit default 0 not null,
    outputtype      varchar(48) default null,
       action      varchar(16) default 'custom' not null
);
create index wp_name_idx on weblog_custom_template(name);
create index wp_link_idx on weblog_custom_template(link);
create index wp_id_idx on weblog_custom_template(websiteid);

create table custom_template_rendition (
    id           varchar(48)  not null primary key,
    templateid   varchar(48) not null,
    template     longvarchar not null,
    templatelang varchar(48),
       type      varchar(16) default 'STANDARD' not null
);

create table bookmark_folder (
    id               varchar(48) not null primary key,
    name             varchar(255) not null,
    websiteid        varchar(48) not null
);
create index fo_weblogid_idx on bookmark_folder( websiteid );

create table bookmark (
    id               varchar(48) not null primary key,
    folderid         varchar(48) not null,
    name             varchar(255) not null,
    description      varchar(255),
    url              varchar(255) not null,
    priority         integer default 100 not null,
    image            varchar(255),
    feedurl          varchar(255)
);
create index bm_folderid_idx on bookmark( folderid );

create table weblogcategory (
    id               varchar(48) not null primary key,
    name             varchar(255) not null,
    description      varchar(255),
    websiteid        varchar(48) not null,
    image            varchar(255),
    position         integer default 0 not null
);
create index wc_weblogid_idx on weblogcategory( websiteid );

create table weblogentry (
    id              varchar(48)  not null primary key,
    anchor          varchar(255)  not null,
    creator         varchar(255)  not null,
    title           varchar(255)  not null,
    text            longvarchar not null,
    pubtime         timestamp(3) null,
    updatetime      timestamp(3)     not null,
    websiteid       varchar(48)  not null,
    categoryid      varchar(48)  not null,
    publishentry    bit default 1 not null,
    link            varchar(255),
    plugins         varchar(255),
    allowcomments   bit default 0 not null, 
    commentdays     integer default 7 not null,
    rightToLeft     bit default 0 not null,
    pinnedtomain    bit default 0 not null,
    locale          varchar(20),
    status          varchar(20) not null,
    summary         longvarchar default null, 
    content_type    varchar(48) default null, 
    content_src     varchar(255) default null,
    search_description varchar(255) default null
);
create index we_weblogid_idx on weblogentry( websiteid );
create index we_categoryid_idx on weblogentry( categoryid );
create index we_pinnedtom_idx on weblogentry(pinnedtomain);
create index we_creator_idx on weblogentry(creator);
create index we_status_idx on weblogentry(status);
create index we_locale_idx on weblogentry(locale);
create index we_combo1_idx on weblogentry(status, pubtime, websiteid);
create index we_combo2_idx on weblogentry(websiteid, pubtime, status);

create table roller_weblogentrytag (
    id              varchar(48)   not null primary key,
    entryid         varchar(48)   not null,
    websiteid       varchar(48)   not null,    
    creator	        varchar(255)   not null,
    name            varchar(255)  not null,
    time            timestamp(3) 	not null
);

create index wet_entryid_idx on roller_weblogentrytag( entryid );
create index wet_weblogid_idx on roller_weblogentrytag( websiteid );
create index wet_creator_idx on roller_weblogentrytag( creator );
create index wet_name_idx on roller_weblogentrytag( name );

create table roller_weblogentrytagagg (
    id              varchar(48)   not null primary key,
    websiteid       varchar(48) ,    
    name            varchar(255)  not null,
    total           integer		  not null,
    lastused        timestamp(3) 	not null
);

create index weta_weblogid_idx on roller_weblogentrytagagg( websiteid );
create index weta_name_idx on roller_weblogentrytagagg( name );
create index weta_lastused_idx on roller_weblogentrytagagg( lastused );
alter table roller_weblogentrytagagg add constraint weta_weblog_tag_uq unique ( websiteid, name );

create table newsfeed (
    id              varchar(48) not null primary key,
    name            varchar(255) not null,
    description     varchar(255) not null,
    link            varchar(255) not null,
    websiteid       varchar(48) not null
);
create index nf_weblogid_idx on newsfeed( websiteid );


create table roller_comment (
    id         varchar(48) not null primary key,
    entryid    varchar(48) not null,
    name       varchar(255),
    email      varchar(255),
    url        varchar(255),
    content    longvarchar,
    posttime   timestamp(3)   not null,
    notify     bit default 0 not null,
    remotehost varchar(128),
    referrer   varchar(255),
    useragent  varchar(255),
    status     varchar(20) not null,
    plugins    varchar(255),
    contenttype varchar(128) default 'text/plain' not null
);
create index co_entryid_idx on roller_comment( entryid );
create index co_status_idx on roller_comment( status );

-- Ping Feature Tables
-- name: short descriptive name of the ping target
-- pingurl: URL to receive the ping
-- conditioncode:
-- lastsuccess:
create table pingtarget (
    id           varchar(48) not null primary key,
    name         varchar(255) not null,
    pingurl      varchar(255) not null,
    conditioncode    integer default 0 not null,
    lastsuccess  timestamp(3),
    autoenabled  bit default 0 not null
);

-- auto ping configurations
-- websiteid:  fk reference to weblog for which this auto ping configuration applies
-- pingtargetid: fk reference to the ping target to be pinged when the weblog changes
create table autoping (
    id            varchar(48) not null primary key,
    websiteid     varchar(48) not null,
    pingtargetid  varchar(48) not null 
);
create index ap_websiteid_idx on autoping( websiteid );
create index ap_pingtid_idx on autoping( pingtargetid );

-- entrytime: timestamp of original entry onto the ping queue
-- pingtargetid: weak fk reference to ping target (not constrained)
-- websiteid: weak fk reference to weblog originating the ping (not constrained)
-- attempts:  number of ping attempts that have been made for this entry
create table pingqueueentry (
    id             varchar(48) not null primary key,
    entrytime      timestamp(3) not null, 
    pingtargetid   varchar(48) not null,  
    websiteid      varchar(48) not null,  
    attempts       integer not null
);
create index pqe_entrytime_idx on pingqueueentry( entrytime );
create index pqe_pingtid_idx on pingqueueentry( pingtargetid );
create index pqe_websiteid_idx on pingqueueentry( websiteid );

create table roller_properties (
    name     varchar(255) not null primary key,
    value    longvarchar
);

create table roller_tasklock (
    id              varchar(48)   not null primary key,
    name            varchar(255)  not null,
    islocked        bit default 0,
    timeacquired    timestamp(3) null,
    timeleased	    integer,
    lastrun         timestamp(3) null,
    client          varchar(255)
);
alter table roller_tasklock add constraint rtl_name_uq unique ( name );
create index rtl_taskname_idx on roller_tasklock( name );

create table roller_hitcounts (
    id              varchar(48) not null primary key,
    websiteid       varchar(48) not null,
    dailyhits	    integer
);
create index rhc_websiteid_idx on roller_hitcounts( websiteid );
create index rhc_dailyhits_idx on roller_hitcounts( dailyhits );

-- Entry attribute: metadata for weblog entries
create table entryattribute (
    id       varchar(48) not null primary key,
    entryid  varchar(48) not null,
    name     varchar(255) not null,
    value    longvarchar not null
);
create index ea_entryid_idx on entryattribute( entryid );
alter table entryattribute add constraint ea_name_uq unique ( entryid, name );


-- OAUTH SUPPORT

-- each record is an OAuth consumer key and secret, can be tied to just one user
create table roller_oauthconsumer (
    consumerkey    varchar(48) not null primary key,
    consumersecret varchar(48) not null,
    username       varchar(48)
);

-- each record is an OAuth accessor, always tied to just one user
create table roller_oauthaccessor (
    consumerkey  varchar(48) not null primary key,
    requesttoken varchar(48),
    accesstoken  varchar(48),
    tokensecret  varchar(48),
    created      timestamp(3) not null,
    updated      timestamp(3) not null,
    username     varchar(48),
    authorized   bit default 0
);

create table rag_properties (
    name     varchar(255) not null primary key,
    value    longvarchar
);


-- PLANET FEED AGGREGATOR

create table rag_planet (
    id              varchar(48) not null primary key,
    handle          varchar(32) not null,
    title           varchar(255) not null,
    description     varchar(255)
);
alter table rag_planet add constraint ragp_handle_uq unique ( handle );


create table rag_group (
    id               varchar(48) not null primary key,
    planet_id        varchar(48) not null,
    handle           varchar(32) not null,
    title            varchar(255) not null,
    description      varchar(255),
    max_page_entries integer default 30,
    max_feed_entries integer default 30,
    cat_restriction  longvarchar,
    group_page       varchar(255)
);
alter table rag_group add constraint ragg_handle_uq unique ( planet_id, handle );


create table rag_subscription (
    id               varchar(48) not null primary key,
    title            varchar(255) not null,
    feed_url         varchar(255) not null,
    site_url         varchar(255),
    author           varchar(255),
    last_updated     timestamp(3),
    inbound_links    integer default -1,
    inbound_blogs    integer default -1
);
alter table rag_subscription add constraint rags_feed_url_uq unique ( feed_url );


create table rag_group_subscription (
    group_id         varchar(48) not null,
    subscription_id  varchar(48) not null
);
create index raggs_gid_idx on rag_group_subscription(group_id); 
create index raggs_sid_idx on rag_group_subscription(subscription_id); 


create table rag_entry (
    id               varchar(48) not null primary key,
    subscription_id  varchar(48) not null,
    handle           varchar(255),
    title            varchar(255),
    guid             varchar(255),
    permalink        longvarchar not null,
    author           varchar(255),
    content          longvarchar,
    categories       longvarchar,
    published        timestamp(3) not null,
    updated          timestamp(3)    
);
create index rage_sid_idx on rag_entry(subscription_id);

-- create a default planet and group
insert into rag_planet (id, handle, title) values ('zzz_default_planet_zzz', 'default', 'Default Planet');
insert into rag_group (id, planet_id, handle, title) values ('zzz_all_group_zzz', 'zzz_default_planet_zzz', 'all', 'Default Group');


-- MEDIA BLOGGING

create table roller_mediafile (
    id              varchar(48) not null primary key,
    name            varchar(255) not null,
    description     varchar(255),
    origpath        varchar(255),
    content_type    varchar(50)  not null,
    copyright_text  varchar(1023),
    directoryid     varchar(48) not null,
    weblogid        varchar(48) not null,
    width           integer,
    height          integer,
    size_in_bytes   integer,
    date_uploaded   timestamp(3) not null,
    last_updated    timestamp(3),
    anchor          varchar(255),
    creator         varchar(255),
    is_public       bit default 0 not null
);

create table roller_mediafiletag (
    id              varchar(48) not null primary key,
    mediafile_id    varchar(48) not null,
    name            varchar(30) not null
);

create table roller_mediafiledir (
    id               varchar(48) not null primary key,
    name             varchar(255) not null,
    description      varchar(255),
    websiteid        varchar(48) not null
);


-- *****************************************************
-- Now add the foreign key relationships

-- user, role, weblog, and permissions

-- page, entry, category, comment

alter table weblog_custom_template add constraint wct_weblogid_fk
    foreign key ( websiteid ) references weblog( id )  ;

alter table custom_template_rendition add constraint ctr_templateid_fk
    foreign key ( templateid ) references weblog_custom_template( id )  ;

alter table weblogentry add constraint we_weblogid_fk
    foreign key ( websiteid ) references weblog( id )  ;

alter table weblogentry add constraint we_categoryid_fk
    foreign key ( categoryid ) references weblogcategory( id )  ;

alter table roller_weblogentrytag add constraint rwtg_entryid_fk
    foreign key ( entryid ) references weblogentry( id )  ;

alter table weblogcategory add constraint wc_weblogid_fk
    foreign key ( websiteid ) references weblog( id )  ;

alter table roller_comment add constraint co_entryid_fk
    foreign key ( entryid ) references weblogentry( id )  ;

alter table entryattribute add constraint att_entryid_fk
    foreign key ( entryid ) references weblogentry( id )  ;

-- bookmark_folder and bookmark

alter table bookmark_folder add constraint fo_weblogid_fk
    foreign key ( websiteid ) references weblog( id )  ;

alter table bookmark add constraint bm_folderid_fk
    foreign key ( folderid ) references bookmark_folder( id )  ;

-- media file foreign key constraints

alter table roller_mediafile add constraint roller_mediafiledir_id_fk
    foreign key (directoryid) references roller_mediafiledir(id)  ;

alter table roller_mediafiletag add constraint roller_mediafile_id_tag_fk
    foreign key (mediafile_id) references roller_mediafile(id)  ;

alter table roller_mediafiledir add constraint mf_weblogid_fk
    foreign key ( websiteid ) references weblog( id )  ;

-- newsfeed

alter table newsfeed add constraint nf_weblogid_fk
    foreign key ( websiteid ) references weblog( id )  ;

-- autoping

alter table autoping add constraint ap_weblogid_fk
    foreign key (websiteid) references weblog(id)  ;

alter table autoping add constraint ap_pingtargetid_fk
    foreign key (pingtargetid) references pingtarget(id)  ;

-- oauth indexes

create index oc_username_idx  on roller_oauthconsumer( username );
create index oc_consumerkey_idx  on roller_oauthconsumer( consumerkey );
