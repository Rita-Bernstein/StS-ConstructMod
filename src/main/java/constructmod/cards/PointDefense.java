package constructmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import constructmod.patches.AbstractCardEnum;
import constructmod.powers.AutoturretPower;
import constructmod.powers.PointDefensePower;
import constructmod.powers.SiegeFormPower;

public class PointDefense extends AbstractConstructCard {
	public static final String ID = "PointDefense";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int POWER_BLOCK_LIMIT = 5;
	private static final int UPGRADE_PLUS_POWER_BLOCK_LIMIT = 5;
	private static final int M_UPGRADE_PLUS_POWER_BLOCK_LIMIT = 89;
	private static final int POOL = 1;

	public PointDefense() {
		super(ID, NAME, "img/cards/"+ID+".png", COST, DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.CONSTRUCTMOD, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);
		this.magicNumber = this.baseMagicNumber = POWER_BLOCK_LIMIT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointDefensePower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new PointDefense();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPGRADE_PLUS_POWER_BLOCK_LIMIT);
		} else if (this.canUpgrade()) {
			this.megaUpgradeName();
			this.upgradeMagicNumber(M_UPGRADE_PLUS_POWER_BLOCK_LIMIT);
		}
	}
}