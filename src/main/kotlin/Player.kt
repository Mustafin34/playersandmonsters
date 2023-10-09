class Player(attack : Int, defense : Int, health : Double, name: String, damageOp : Int, damageEnd : Int):Creature(attack, defense, health, damageOp, damageEnd, name) {
    val maxHealth = health
    var quaHeal = 4
    fun heal(){
        if (quaHeal != 0){
            var healka  = maxHealth * 0.3 + health
            if(healka<=maxHealth) health = healka
            else health = maxHealth
            quaHeal--
        }else println("зелья исцедления закончились")
    }
}