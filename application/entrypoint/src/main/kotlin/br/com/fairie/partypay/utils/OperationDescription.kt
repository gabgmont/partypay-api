package br.com.fairie.partypay.utils

//Authentication Controller
const val AUTH_TAG_TITLE = "Authentication"
const val AUTH_TAG_DESCRIPTION = "Authentication Operations."

const val POST_AUTHENTICATION_VALUE = "Generate Bearer token to authenticate other requests."
const val POST_AUTHENTICATION_NOTES = ""

//User Controller
const val USER_TAG_TITLE = "User"
const val USER_TAG_DESCRIPTION = "User Operations."

const val GET_USER_OPERATION_VALUE = "Retrieve registered users."
const val GET_USER_OPERATION_NOTES = """
    -Operation used to get user from Database.
    
    CPF can be informed as a parameter to search for specific user. If left blank retrieves all users from database."""

//Menu Controller
const val MENU_TAG_TITLE = "Menu"
const val MENU_TAG_DESCRIPTION = "Menu Operations."

const val GET_MENU_OPERATION_VALUE = "Browse restaurants menu"
const val GET_MENU_OPERATION_NOTES = ""

const val GET_MENU_CATEGORY_OPERATION_VALUE = "Browse restaurant menu category"
const val GET_MENU_CATEGORY_OPERATION_NOTES = ""

const val GET_MENU_ORDER_OPERATION_VALUE = "Browse restaurant menu order"
const val GET_MENU_ORDER_OPERATION_NOTES = ""

//Session Controller
const val SESSION_TAG_TITLE = "Session"
const val SESSION_TAG_DESCRIPTION = "Session management operations."

const val CREATE_SESSION_OPERATION_VALUE = "Creates new session."
const val CREATE_SESSION_OPERATION_NOTES = ""

const val ADD_ORDER_SESSION_OPERATION_VALUE = "Adds order to current session."
const val ADD_ORDER_SESSION_OPERATION_NOTES = ""

const val ADD_USER_SESSION_OPERATION_VALUE = "Adds user to current session."
const val ADD_USER_SESSION_OPERATION_NOTES = ""

const val CLOSE_SESSION_OPERATION_VALUE = "Ends session and calculate check."
const val CLOSE_SESSION_OPERATION_NOTES = ""