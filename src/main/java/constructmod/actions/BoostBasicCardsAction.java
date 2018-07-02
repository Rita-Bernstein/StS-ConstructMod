package constructmod.actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

import constructmod.cards.AbstractConstructCard;
import com.megacrit.cardcrawl.cards.*;

public class BoostBasicCardsAction extends AbstractGameAction
{
	
	boolean isMultiplier;
	
    public BoostBasicCardsAction(int amount,boolean isMultiplier) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.amount = amount;
        this.isMultiplier = isMultiplier;
    }
    
    @Override
    public void update() {
    	for (final AbstractCard c:AbstractDungeon.player.drawPile.group) {
        	BoostCard(c);
        }
        for (final AbstractCard c:AbstractDungeon.player.hand.group) {
        	if (BoostCard(c)) c.flash();
        }
        for (final AbstractCard c:AbstractDungeon.player.discardPile.group) {
        	BoostCard(c);
        }
        this.isDone = true;
    }
    
    public boolean BoostCard(AbstractCard c) {
    	boolean wasBoosted = false;
    	if (c.cardID.startsWith("Strike_")) {
    		wasBoosted = true;
    		if (this.isMultiplier) c.baseDamage *= this.amount; 
    		else c.baseDamage += this.amount;
    	}
    	if (c.cardID.startsWith("Defend_")) {
    		wasBoosted = true;
    		if (this.isMultiplier) c.baseBlock *= this.amount; 
    		else c.baseBlock += this.amount;
    	}
    	return wasBoosted;
    }
}
