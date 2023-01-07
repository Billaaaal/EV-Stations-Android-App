package com.example.evchargingstationsapp

import android.util.Log

fun refactorQuery(query:String):String {

    val keywords = arrayOf(
        "Tesla",
        "SemaConnect",
        "ChargePoint",
        "Blink",
        "Evgo",
        "Electrify America",
        "Greenlots",
        "Plugless",
        "Volta",
        "AeroVironment",
        "EV charging",
        "electric vehicle charging",
        "EVSE",
        "EVgo Freedom Station",
        "EV Connect",
        "ChargeHub",
        "ChargePoint Home",
        "EV Box",
        "Webasto",
        "AddEnergie",
        "EV Solutions",
        "EVTripPlanner",
        "Fastned",
        "GE WattStation",
        "Ionity",
        "JuiceBar",
        "Leviton",
        "Mercedes-Benz Charging",
        "Myenergi",
        "Nissan No Charge to Charge",
        "Porsche Charging",
        "Powertree",
        "Pulse Charging",
        "Renault Mobility",
        "ChargePoint Network",
        "Schneider Electric EVlink",
        "Siemens VersiCharge",
        "SmartCharge",
        "Sustainable Energy",
        "The New Motion",
        "The EV Network",
        "Ubitricity",
        "Volkswagen We Charge",
        "Briarcliff Lodge",
        "Circuit Électrique",
        "Ecotap",
        "FLO",
        "Get Charged",
        "NRG eVgo",
        "Pod Point",
        "PlugSurfing",
        "Sun Country Highway",
        "V2G EV",
        "Volta Industries",
        "Charge Master",
        "Drayton Manor Theme Park",
        "ECOtality",
        "Engenie",
        "EV Charging",
        "eCharge",
        "eMotorWerks",
        "Fortum Charge & Drive",
        "JET Charge",
        "Juice Technology",
        "Kinetik",
        "Mobilise",
        "Octopus Energy",
        "Polar Network",
        "Rapid Charge Network",
        "Recargo",
        "Rydes",
        "Tesla Supercharger",
        "Zap-Map",
        "Supercharger",
    )

    val connectors = arrayOf(
        "J1772",
        "CHAdeMO",
        "CCS",
        "Type 2",
        "Tesla Supercharger"
    )

    val querySearchInputToTest = query

    var listOfConnectorsFound = connectors.filter { querySearchInputToTest.contains(it, ignoreCase = true) }

    var listOfKeywordsFound = keywords.filter { querySearchInputToTest.contains(it, ignoreCase = true) }

    var listOfLocationFound = querySearchInputToTest

    for (connector in listOfConnectorsFound) {
        listOfLocationFound = listOfLocationFound.replace("(?i)$connector".toRegex(), "")

        // ...
    }

    for (keyword in listOfKeywordsFound) {
        listOfLocationFound = listOfLocationFound.replace("(?i)$keyword".toRegex(), "")

        // ...
    }

    var connectorsFound = listOfConnectorsFound.joinToString(" ")

    var keywordsFound = listOfKeywordsFound.joinToString(" ")



    //println("Query entered : $querySearchInputToTest")
    //println("Connectors found : $listOfConnectorsFound")
    //println("Keywords found : $listOfKeywordsFound")
    //println("Location : $listOfLocationFound")

    Log.i("Blahahahaha", "Query entered : $querySearchInputToTest")
    Log.i("Blahahahaha", "Connectors found : $listOfConnectorsFound")
    Log.i("Blahahahaha", "Keywords found : $listOfKeywordsFound")
    Log.i("Blahahahaha", "Location : $listOfLocationFound")

    var url = "https://ev-charging-stations-search.p.rapidapi.com/search-by-location?near=$listOfLocationFound&type=$connectorsFound&limit=100&query=$keywordsFound"





    //extract connectors, keywords into two different variables and remove them from the original strings
    // and then create a variable for the location
    //that has all the remaining terms (adress, city, state, zip, country,etc...)
    //a good website to run Kotlin Code online : https://www.tutorialspoint.com/online_kotlin_compiler.php

    return url







}