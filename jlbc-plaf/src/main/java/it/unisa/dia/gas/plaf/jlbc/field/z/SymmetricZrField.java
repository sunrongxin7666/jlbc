package it.unisa.dia.gas.plaf.jlbc.field.z;

import it.unisa.dia.gas.plaf.jlbc.field.base.AbstractField;
import it.unisa.dia.gas.plaf.jlbc.util.math.BigIntegerUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Angelo De Caro (angelo.decaro@gmail.com)
 */
public class SymmetricZrField extends AbstractField<SymmetricZrElement> {
    protected BigInteger order;
    protected BigInteger halfOrder;
    
    protected SymmetricZrElement nqr;
    protected int fixedLengthInBytes;
    protected BigInteger twoInverse;


    public SymmetricZrField(BigInteger order) {
        this(new SecureRandom(), order, null);
    }

    public SymmetricZrField(Random random, BigInteger order) {
        this(random, order, null);
    }

    public SymmetricZrField(BigInteger order, BigInteger nqr) {
        this(new SecureRandom(), order, nqr);
    }

    public SymmetricZrField(Random random, BigInteger order, BigInteger nqr) {
        super(random);
        this.order = order;
        this.orderIsOdd = BigIntegerUtils.isOdd(order);

        this.fixedLengthInBytes = (order.bitLength() + 7) / 8;

        this.twoInverse = BigIntegerUtils.TWO.modInverse(order);

        this.halfOrder = order.divide(BigInteger.valueOf(2));

        if (nqr != null)
            this.nqr = newElement().set(nqr);
    }


    public SymmetricZrElement newElement() {
        return new SymmetricZrElement(this);
    }

    public BigInteger getOrder() {
        return order;
    }

    public int getLengthInBytes() {
        return fixedLengthInBytes;
    }
    
}
