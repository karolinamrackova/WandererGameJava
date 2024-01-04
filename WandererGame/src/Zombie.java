public class Zombie extends Monster {
    public Zombie() {
        super();
        if (level > 5){
            maxHP = level  * monsterIndex * d6() * 10;
            currentHP = maxHP;
            dp = monsterIndex * d6() * 6;
            sp = monsterIndex * d6() * 6;
        } else {
            maxHP = level / 2 * monsterIndex * d6()+10;
            currentHP = maxHP;
            dp = monsterIndex / 2 * d6()+5;
            sp = monsterIndex * d6()+5;
        }
        img = "img/zombie.png";
    }
}
