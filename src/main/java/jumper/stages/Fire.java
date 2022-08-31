package jumper.stages;

import engimon.core.Scene;
import engimon.particles.ParticleEmmiter;

import java.util.Iterator;

public class Fire extends ParticleEmmiter<FireParticle> {
    public Fire(Scene scene) {
        super(scene);
        setMaxParticles(10000);
    }

    @Override
    public synchronized void customTick(double deltaTime) {
        Iterator<FireParticle> pIterator = getParticles().iterator();
        FireParticle p;
        while(pIterator.hasNext()) {
            p = pIterator.next();
            double livedFor = p.getLivedFor();
            double lifeSpan = p.getLifeSpan();
            double newLivedFor = livedFor+deltaTime;
            if (newLivedFor > lifeSpan) {
                p.kill();
                pIterator.remove();
                continue;
            }
            p.setLivedFor(newLivedFor);
        }

        double chance = deltaTime * (getMaxParticles() - getParticles().size()) / getMaxParticles() * 10;
        
        if (getRandom().nextDouble() < chance) {
            FireParticle particle = new FireParticle(getScene());
            getParticles().add(particle);
            particle.setLocalX(getRandom().nextDouble()*50-25);
            
            addChild(particle);
            
        }

    }
}
