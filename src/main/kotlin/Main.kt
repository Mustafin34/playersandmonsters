fun main(args: Array<String>) {
    println("Приветствую тебя странник, мое имя Хастальф, я твой проводник нашем темном подземелье.\nЧто бы пройти его тебе необходимо преодалеть три уровня, на каждом из которых тебе встретятся монстры,\nкаждый уровень охраняется несколькими охранниками и главарем.\nНо для начала назови себя.")
    var nameHeroes = readLine()
    if(nameHeroes == "") nameHeroes = "n"
    while (nameHeroes?.filter { it in 'а'..'я'|| it in 'А'..'Я'} != nameHeroes || !nameHeroes!![0].isUpperCase()){
        println("Имя должно содержать только одно слово и только кириллицей и начинаться с большой буквы")
        nameHeroes = readLine()
        if(nameHeroes == "") nameHeroes = "n"
    }
    println("Хорошо $nameHeroes, теперь прикоснись к волшебному камню чтобы увидеть на что ты способен.")
    val damageF = (1..10).random()
    val player = Player(attack = (1..30).random(), defense = (1..30).random(), health = (100..300).random().toDouble(), name = nameHeroes, damageOp = damageF, damageEnd = (damageF..20).random() )
    println("Итак, $nameHeroes, твои способности впечетляют. Вот твоя карточка:")
    player.showCard()
    println("Ну что я вижу ты готов, добро пожаловать в подземелье!")
    var tekName =""
    for (i in 1..3){
        println("Уровень $i, приготовся!")
        var sequrity: MutableMap<String?,Monster> = mutableMapOf()
        println("Вот и охраники: ")
        for(j in 1..i) {
            tekName = nameBuilder()
            sequrity.put(tekName,Monster(attack = (1..10*i).random(), defense = (1..10*i).random(), health = (10*i..30*i).random().toDouble(), name = tekName, damageOp = 1*i , damageEnd = 3*i))
            sequrity[tekName]!!.showCard()
        }
        while (sumHealthSeq(sequrity)!=0.0){
            if(player.health == 0.0){
                println("Оу мой друг покойся с миром...\nконец игры")
                return
            }
            println("Атакуем или Лечимся?")
            var action : String? = readLine()
            while(action != "Лечимся" && action != "Атакуем"){
                println("введите дословно")
                action = readLine()
            }
            if(action == "Лечимся")player.heal()
            println("какого соперника будем атакавать в этом ходу?")
            var proto : String? = readLine()
            while (!isProto(sequrity,proto)){
                println("введите дословно")
                proto = readLine()
            }
            player.attack(sequrity[proto])
            println("Ситуация следующая:")
            for (seq in sequrity){
                seq.value.attack(player)
                println("${seq.key}: ${seq.value.health}")
            }
            println("${player.name}: ${player.health}")
        }
        println("С этими разобрались, дальше босс!")
        tekName = nameBuilder()
        var boss = Monster(attack = (1..30).random(), defense = (1..30).random(), health = (100..150).random().toDouble(), name = tekName, damageOp = 1 , damageEnd = 15)
        boss.showCard()
        while (boss.health!=0.0){
            if(player.health == 0.0){
                println("Оу мой друг покойся с миром...\nконец игры")
                return
            }
            println("Атакуем или Лечимся?")
            var action : String? = readLine()
            while(action != "Лечимся" && action != "Атакуем"){
                println("введите дословно")
                action = readLine()
            }
            if(action == "Лечимся")player.heal()
            player.attack(boss)
            boss.attack(player)
            println("Ситуация следующая:")
            println("${player.name}: ${player.health}")
            println("${boss.name}: ${boss.health}")
        }
        println("Отлично босс побежден идем на следующий уровень!")
    }
    println("Поздравляю мой друг ты прошел подземелье!!!")
}

fun nameBuilder():String{
    var name = ""
    name += ('А'..'Я').random()
    for (i in 0 .. (1..12).random()) name += ('а'..'я').random()
    return name
}

fun sumHealthSeq(seqs:MutableMap<String?,Monster>): Double {
    var sum = 0.0
    for(seq in seqs){
        sum += seq.value.health
    }
    return sum
}

fun isProto(protos:MutableMap<String?,Monster>, vvod:String?):Boolean{
    for(prot in protos){
        if(prot.key == vvod) return true
    }
    return false
}
