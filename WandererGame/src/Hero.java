import java.util.Random;

public class Hero extends Character {
    /*Hero:
HP: 20 + 3 * d6
DP: 2 * d6
SP: 5 + d6*/
    int heroLevel = 1;

    public Hero() {
        super();
        maxHP = 30 + d6Multiple(3);
        currentHP = maxHP;
        dp = 2 * d6();
        sp = 5 + d6();
        positionX = 0;
        positionY = 0;
    }

  /*  After successfully won battle the character is leveling up.
    His max HP increases by d6.
    His DP increases by d6.
    His SP increases by d6.*/

    public void levelUp() {
        maxHP = maxHP + d6();
        dp = dp + d6();
        sp = sp + d6();
        heroLevel++;
    }

    /*When entering a new area the hero has:
    10% chance to restore all his HP.
    40% chance to restore the third of his HP.
    50% chance to restore 10% of his HP.*/
    public void newLevel() {
        int index = random.nextInt(10) + 1;
        if (index == 1) {
            this.currentHP = this.maxHP;
        } else if (index > 1 && index <= 5) {
            this.currentHP = this.currentHP + (this.maxHP * 0.33);
            if (this.currentHP > maxHP) {
                this.currentHP = maxHP;
            }
        } else if (index > 5) {
            this.currentHP = this.currentHP + (this.maxHP * 0.1);
            if (this.currentHP > maxHP) {
                this.currentHP = maxHP;
            }
        }
    }

    public void redPotion() {
        this.currentHP = this.maxHP;
        this.sp += 3;
        this.dp += 3;

    }

    public void greenPotion() {
        this.currentHP = 10;
        this.sp -= 3 * level;
        this.dp -= 3 * level;

    }

    public void gotShield() {

        this.dp += level * 12;

    }

    public void gotSword() {

        this.sp += level * 12;

    }


}
