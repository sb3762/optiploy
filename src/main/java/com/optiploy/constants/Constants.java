package com.optiploy.constants;


public class Constants 
{	
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";
    
    /**
     * The request scope attribute that holds the job list
     */
    public static final String JOB_LIST = "jobList";
    
    /**
     * The request scope attribute that holds the role list
     */
    public static final String ROLE_LIST = "roleList";
    
    /**
     * The request scope attribute that holds the release list
     */
    public static final String RELEASE_LIST = "releaseList";
    
    /**
     * The request scope attribute that holds the application list
     */
    public static final String APPLICATION_LIST = "applicationList";
    
    /**
     * The request scope attribute that holds the environment list
     */
    public static final String ENVIRONMENT_LIST = "environmentList";
    
    /**
     * The request scope attribute that holds the function list
     */
    public static final String FUNCTION_LIST = "functionList";
    
    /**
     * The request scope attribute that holds the progress list
     */
    public static final String PROGRESS_LIST = "progressList";
    
    /**
     * The request scope attribute that holds the module list
     */
    public static final String MODULE_LIST = "moduleList";
        
    /**
     * The request scope attribute that holds the drool list
     */
    public static final String DROOL_LIST = "droolList";
   
    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";
    
    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";
    
    /**
     * The name of the available releases list, a request-scoped attribute
     * when adding/editing a release.
     */
    public static final String AVAILABLE_RELEASES = "availableReleases";
    
    /**
     * The name of the available applications list, a request-scoped attribute
     * when adding/editing an application.
     */
    public static final String AVAILABLE_APPLICATIONS = "availableApplications";
          
    /**
     * The name of the available drool list, a request-scoped attribute
     * when adding/editing a drool.
     */
    public static final String AVAILABLE_DROOLS = "availableDrools";
    
    /**
     * The name of the available function list, a request-scoped attribute
     * when adding/editing a function.
     */
    public static final String AVAILABLE_FUNCTIONS = "availableFunctions";
    
    /**
     * The name of the available module list, a request-scoped attribute
     * when adding/editing a module.
     */
    public static final String AVAILABLE_MODULES = "availableModules";
    
    /**
     * The name of the available progress list, a request-scoped attribute
     * when adding/editing a progress.
     */
    public static final String AVAILABLE_PROGRESSES = "availableProgresses";
        
    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";    
    
    /**
     * Success message to be passed to tag library
     */
    public static final String MESSAGES_KEY = "successMessages";
    
    /**
     * Version of application
     */
    public static final String VERSION = "Version";
    
    /**
     * Servlet URL to be hit for status
     */
    public static final String SERVLET_URL = "ServletUrl";
    
    /**
     * Interval which build thread will sleep
     */
    public static final String BUILD_MONITOR_INTERVAL = "BuildMonitorInterval";
    
    /**
     * Administration email address
     */
    public static final String ADMIN_EMAIL = "adminEmail";
    
    /**
     * Administration email address subject line
     */
    public static final String ADMIN_EMAIL_SUBJECT = "Universal Deployment Tool Administration Email";
    
    /**
     * Administration local testing
     */
    public static final String ADMIN_LOCAL_TEST = "LocalTest";
    
    /**
     * Agent port
     */
    public static final String AGENT_PORT = "Port";
    
    /**
     * Agent instances
     */
    public static final String AGENT_INSTANCES = "Instances";
    
    /**
     * Agent port
     */
    public static final String AGENT_DECRIPTION = "Decription";
    
    /**
     * Instance ready status
     */
    public static final String INSTANCE_STATUS_READY = "Ready";
    
    /**
     * Instance Running status
     */
    public static final String INSTANCE_STATUS_RUNNING = "Running";
    
    /**
     * Agent running status
     */
    public static final String AGENT_STATUS_RUNNING = "Running";
    
    /**
     * Agent busy status
     */
    public static final String AGENT_STATUS_BUSY = "Busy";
    
    /**
     * Agent down status
     */
    public static final String AGENT_STATUS_DOWN = "Down";
    
    /**
     * Agent shutdown status
     */
    public static final String AGENT_STATUS_SHUTDOWN = "Shutdown";
    
    /**
     * Agent low disk space status
     */
    public static final String AGENT_STATUS_LOWDISK = "Lowdisk";
    
    /**
     * Agent minimum disk space
     */
    public static final String AGENT_MIN_DISK_SPACE = "MinimumDiskSpace";
    
    /**
     * Agent low disk space status
     */
    public static final String AGENT_STATUS_INCOMPATIBLE = "Incompatible";
    
    /**
     * Agent priority
     */
    public static final String AGENT_PRIORITY = "Priority";
    
    /**
     * Agent priority
     */
    public static final String AGENT_VERSION = "Version";
    
    /**
     * Agent log directory
     */
    public static final String AGENT_LOG_DIRECTORY = "LogDirectory";
    
    /**
     * Agent build directory
     */
    public static final String AGENT_BUILD_DIRECTORY = "BuildDirectory";
    
    /**
     * Agent build directory
     */
    public static final String AGENT_BUILD_TIMEOUT = "BuildTimeout";
    
    /**
     * Agent hard stop
     */
    public static final String AGENT_HARD_STOP = "hardStop";
    
    /**
     * Agent startInstances
     */
    public static final String AGENT_START_INSTANCES = "startInstances";
    
    /**
     * Agent clean up file
     */
    public static final String AGENT_CLEANUP_FILES = "CleanupFiles";
    
    /**
     * Agent windows make file
     */
    public static final String AGENT_MAKE_FILE_WINDOWS = "MakeFileWindows";
    
    /**
     * Agent unix make file
     */
    public static final String AGENT_MAKE_FILE_UNIX = "MakeFileUnix";
    
    /**
     * Build label
     */
    public static final String BUILD_LABEL = "Label";
    
    /**
     * Build status
     */
    public static final String BUILD_STATUS = "Status";
    
    /**
     * Build progress
     */
    public static final String BUILD_PROGRESS = "Progress";
    
    /**
     * Build attribute start
     */
    public static final String BUILD_ATTRIBUTE_START = "attr.";
    
    /**
     * Build success status
     */
    public static final String BUILD_STATUS_REASON = "Reason";
    
    /**
     * Build success status
     */
    public static final String BUILD_STATUS_SUCCESS = "Success";
    
    /**
     * Build failure status
     */
    public static final String BUILD_STATUS_FAILURE = "Failure";
    
    /**
     * Build running status
     */
    public static final String BUILD_STATUS_RUNNING = "Running";
    
    /**
     * Build starting status
     */
    public static final String  BUILD_STATUS_STARTING = "Starting";
    
    /**
     * Build complete status
     */
    public static final String  BUILD_STATUS_COMPLETE = "Complete";
    
    /**
     * Build error status
     */
    public static final String BUILD_STATUS_ERROR = "Error";
    
    /**
     * Build log id
     */
    public static final String BUILD_LOG_ID = "logId";
    
    /**
     * Request start build
     */    
    public static final String REQUEST_TYPE_START_BUILD = "startBuild";

    /**
     * Request build status
     */
    public static final String REQUEST_TYPE_BUILD_STATUS = "buildStatus";

    /**
     * Request stop build
     */
    public static final String REQUEST_TYPE_STOP_BUILD = "stopBuild";

    /**
     * Request shutdown build
     */
    public static final String REQUEST_TYPE_SHUTDOWN = "shutdown";
    
    /**
     * Modes
     */
    public static final String MODE = "mode";
    public static final String MODE_ADD = "add";
    public static final String MODE_UPDATE = "update";
    public static final String MODE_DETAIL = "detail";
    public static final String MODE_DELETE = "delete";
    public static final String MODE_PROFILE = "profile";
    public static final String MODE_CANCEL = "cancel";
    public static final String MODE_USER = "user";
    public static final String MODE_ROLE = "role";
    public static final String MODE_JOB = "job";
    public static final String MODE_RELEASE = "release";
    public static final String MODE_APPLICATION = "application";
    public static final String MODE_ENVIRONMENT = "environment";
    public static final String MODE_FUNCTION = "function";
    public static final String MODE_MODULE = "module";
    public static final String MODE_PROGRESS = "progress";
    
    
}
