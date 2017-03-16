package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.Projectile;
import data.WeaponData;
import util.Util;

public class MachineGun extends Weapon implements WeaponData {
	private final float iRpf, mRpf, ddRpf;
	private float dRpf;
	public MachineGun(Player owner) {
		super(owner, 0, MACHINEGUN_LEGNTH, MACHINEGUN_WIDTH);
		this.iRpf = MACHINEGUN_INITIAL_RPM/60f/UPS;
		this.mRpf = MACHINEGUN_MAX_RPM/60f/UPS;
		this.ddRpf = MACHINEGUN_RPM_PS_PS/UPS;
		this.rpf = iRpf;
	}

	@Override
	public void tick() {
		if (owner.getActiveWeapon()==this&&owner.getMouseControl(SHOOT_1)) {
			dRpf+=ddRpf;
			rpf+=dRpf;
			if (rpf>mRpf) rpf = mRpf;
		}
		else {
			dRpf = 0;
			rpf = iRpf;
		}
		super.tick();
	}

	@Override
	protected void use() {//FIXME
		float angle = Util.getAngleSpread(owner.getFacing(), MACHINEGUN_COF), speed = Util.getSpread(MACHINEGUN_SPEED, MACHINEGUN_SPEED_SPREAD);
		float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
		float startingOffset = Util.getSpread(PLAYER_SIZE);
		float x = owner.getXCenter()+Util.getXComp(angle, startingOffset), y = owner.getYCenter()-Util.getYComp(angle, startingOffset);
		Game.addEntity(new Projectile(MACHINEGUN_DAMAGE, MACHINEGUN_RECOIL, MACHINEGUN_SIZE, owner.getColor(), x, y, dX, dY));
		owner.recoil(owner.getFacing(), -MACHINEGUN_RECOIL);
	}

	@Override
	public String toString() {
		return "Machine Gun";
	}
}
