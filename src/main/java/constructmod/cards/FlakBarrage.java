package constructmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import constructmod.ConstructMod;
import constructmod.actions.DealMultiRandomDamageAction;
import constructmod.patches.AbstractCardEnum;

public class FlakBarrage extends AbstractCycleCard {
	public static final String ID = ConstructMod.makeID("FlakBarrage");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 0;
	private static final int TIMES = 4;
	//private static final int UPGRADE_PLUS_TIMES = 1;
	private static final int M_UPGRADE_PLUS_TIMES = 2;
	private static final int POOL = 1;

	public FlakBarrage() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.CONSTRUCTMOD, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY, POOL);
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = TIMES;
	}
	
	@Override
	public boolean canCycle() {
		AbstractPlayer p = AbstractDungeon.player;
		return super.canCycle() && this.upgraded &&
				(!p.hasPower(StrengthPower.POWER_ID) || p.getPower(StrengthPower.POWER_ID).amount <= 0);
				
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DealMultiRandomDamageAction(
				new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), this.magicNumber, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
	}

	@Override
	public AbstractCard makeCopy() {
		return new FlakBarrage();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		} else if (this.canUpgrade()) {
			this.megaUpgradeName();
			this.upgradeMagicNumber(M_UPGRADE_PLUS_TIMES);
		}
	}
}
