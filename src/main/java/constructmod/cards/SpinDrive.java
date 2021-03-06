package constructmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import constructmod.ConstructMod;
import constructmod.patches.AbstractCardEnum;
import constructmod.powers.SpinDrivePower;

public class SpinDrive extends AbstractConstructCard {
	public static final String ID = ConstructMod.makeID("SpinDrive");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;
	private static final int UPGRADE_NEW_COST = 2;
	private static final int FREE_DRAWS = 3;
	private static final int M_UPGRADE_PLUS_FREE_DRAWS = 5;
	private static final int POOL = 1;

	public SpinDrive() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.CONSTRUCTMOD, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF, POOL);
		this.baseMagicNumber = this.magicNumber = FREE_DRAWS;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SpinDrivePower(p,this.magicNumber),this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new SpinDrive();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(UPGRADE_NEW_COST);
		} else if (this.canUpgrade()) {
			this.megaUpgradeName();
			this.upgradeMagicNumber(M_UPGRADE_PLUS_FREE_DRAWS);
		}
	}
}
