fun main () {
    var userInput: String
    var randomNumber: String
    var givenUp: String? = null


    val createUniqueFourDigitNumber = {(0..9).shuffled().take(4).joinToString("")}
    //createUniqueFourDigitNumber.forEach {randomNumber +=it }
    randomNumber = createUniqueFourDigitNumber()
    println(randomNumber)

    do {
        println(if (givenUp != "n") "Guess the number (it has 4 digits and each digit exists only once): $randomNumber" else "Guess again")
        userInput = readLine().toString()
        if (howManyUniqueCharsInString(userInput) < 4)
            println("No repeating digits allowed")
            else {
                println(
                    checkWhichCharsAreInBothStrings(
                        userInput,
                        randomNumber
                    ) + "\n RP = Right position, WP = Wrong position, NC = Not contained"
                )
                if (randomNumber != userInput) {
                    println("Do you want to give up? (y/n) ")
                    givenUp = readStringInput(mutableListOf("y", "n"))
                    println(if (givenUp == "y") "Random number is $randomNumber" else continue)
                } else {
                    println("Congratulations you have guessed the number")
                }
            }
    }
    while((userInput != randomNumber) && givenUp != "y")
}

/**
 * Returns user input if it is in the list which is given as parameter
 */
fun readStringInput (allowed: MutableList<String>): String {
    var input: String
    do {
        println("Allowed inputs: $allowed")
        input = readLine().toString()
    }while(!allowed.contains(input))
    return  input
}

/**
 * Returns a list of elements which state if the
 * RP = Right position, WP = contains but wrong position NC = not contained
 */
fun checkWhichCharsAreInBothStrings (userInput: String, randomNumber: String): MutableList<String> {
    val positionRightList = mutableListOf<String>()

    userInput.forEachIndexed { index, c ->
        positionRightList.add(
            when {
                randomNumber[index] == c -> "RP"
                randomNumber.contains(c.toString())  -> "WP"
                else -> "NC"
            }

            //alte version - funktioniert aber da werden bei doppel vorkommenden Zahlen im Input "WP" angegeben und nicht "NC" bei zwei indizes weiter nach rechts in der Liste
                    /*if(randomNumber[index] == c ) "RP"
            else if(randomNumber.contains(c.toString())) {
                if(randomNumber.reversed().substring(index).contains(c))
                    "NC"
                else
                    "WP"
            }
            else "NC" */
                    )
                }

    return positionRightList
}

/**
 * Returns how many unique chars there are in a string
 */
fun howManyUniqueCharsInString(input: String): Int  {
    val list = mutableListOf<Char>()
    input.forEach{if(!list.contains(it)) list.add(it)}
    return list.size
}

/**
 * Creates 4 digit number
 * Deprecated version
 */
fun createFourDigitNumber(): String {
    var fourDigitNumber  = ""
   // val rangeList = {(0..9).random()}

   while(fourDigitNumber.length < 4){
       val num = (0..9).random().toString()
        if (!fourDigitNumber.contains(num)) fourDigitNumber +=num
    }

   return fourDigitNumber
}