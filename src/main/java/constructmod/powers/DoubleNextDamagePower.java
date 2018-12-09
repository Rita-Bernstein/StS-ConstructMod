//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package constructmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import constructmod.ConstructMod;

public class DoubleNextDamagePower extends AbstractPower {
    public static final String POWER_ID = ConstructMod.makeID("DoubleNextDamage");
    public static final String NAME = "Double Next Damage";
    public static final String[] DESCRIPTIONS = new String[] {
            "Your next attack this turn deals double damage."
    };

    public DoubleNextDamagePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("doubleDamage");
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.priority = 6;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m){
        if (card.type == AbstractCard.CardType.ATTACK){
            if (this.amount == 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            }
        }
    }

    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public float atDamageGive(float damage, DamageType type) {
        return type == DamageType.NORMAL ? damage * 2.0F : damage;
    }

}
