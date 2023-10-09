abstract class Creature(var attack : Int, var defense: Int, var health: Double, var damageOp: Int , var damageEnd : Int, var name : String) {

    init {
        require(attack in 1..30, {"Атака должна быть в диапазоне от 1 до 30"})
        require(defense in 1..30, {"Защита должна быть в диапазоне от 1 до 30"})
        require(health > 0.0, {"Здоровье должно быть больше нуля"})
        require(damageOp < damageEnd, {"Начало урон диапазона должно быть меньше конца"})
        require(damageOp > 0 && damageEnd > 0, {"Урон должен лежать в положительном диапазоне"})
        require(name != "", {"У существа должно быть имя"})
        require(name.filter { it in 'а'..'я'|| it in 'А'..'Я'} == name && name[0].isUpperCase(), {"Имя должно начинаться с заглавной буквы и быть написаным на кириллице"})
    }

    fun attack(proto: Creature?){
        var modAtt = attack - proto!!.defense + 1
        if(modAtt < 1) modAtt = 1
        for(i in 1..modAtt){
            if((1..6).random()>4){
                var damage = (damageOp..damageEnd).random()
                proto.health = proto.defense.toDouble() - damage
                if(proto.health < 0.0) proto.health = 0.0
                break
            }
        }
    }

    fun showCard(){
        println("Существо: $name\nатака: ${attack}\nзащита: ${defense}\nдиапазон урона: от ${damageOp} до ${damageEnd}\nзапас здоровья: ${health.toInt()}\n")
    }
}