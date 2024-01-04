public class Skeleton extends Monster {
    /*Monster Level X:
HP: 2 * X * d6
DP: X/2 * d6
SP: X * d6
*/
    public Skeleton() {
        super();
        img = "img/Skeleton.png";
        if (level > 5){
            maxHP = level  * monsterIndex * d6() * 5;
            currentHP = maxHP;
            dp = monsterIndex * d6() * 4;
            sp = monsterIndex * d6() * 4;
        } else {
            maxHP = level / 2 * monsterIndex * d6();
            currentHP = maxHP;
            dp = monsterIndex / 2 * d6();
            sp = monsterIndex * d6();
        }

    }
}
