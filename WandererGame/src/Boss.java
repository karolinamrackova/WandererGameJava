public class Boss extends Monster {
    /*Boss Level X:
HP: 2 * X * d6 + d6
DP: X/2 * d6 + d6 / 2
SP: X * d6 + X*/
    public Boss() {
        super();
        img = "img/boss.png";
        if (level > 5) {
            maxHP = level/1.5 * monsterIndex * d6() + d6();
            currentHP = maxHP;
            dp = monsterIndex  * d6() + d6Multiple(2);
            sp = monsterIndex * d6() + monsterIndex*4;
        } else {
            maxHP = 2 * monsterIndex * d6() + d6();
            currentHP = maxHP;
            dp = monsterIndex / 2 * d6() + d6();
            sp = monsterIndex * d6() + monsterIndex * 2;
        }

    }
}
