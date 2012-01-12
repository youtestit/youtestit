/*
 *   YouTestit source code:
 *   ======================
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *  
        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Links:
 *   ======
 *   Homepage : http://www.youtestit.org
 *   Git      : https://github.com/youtestit
 */
package org.youtestit.datamodel.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.youtestit.datamodel.enums.SeleniumActionType;


/**
 * Instruction.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 10, 2012
 */
@Entity
public class Instruction implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID = 1199772951902939237L;

    /** The uid. */
    @Id
    @GeneratedValue
    private long               uid;

    /** The type. */
    @Enumerated(EnumType.STRING)
    private SeleniumActionType type;

    /** The target. */
    private String             target;

    /** The value. */
    private String             value;

    /** The error message. */
    private String             errorMessage;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new instruction.
     */
    public Instruction() {
        super();
    }


    /**
     * Instantiates a new instruction.
     * 
     * @param type the type
     * @param target the target
     * @param value the value
     */
    public Instruction(final SeleniumActionType type, final String target, final String value) {
        super();
        this.type = type;
        this.target = target;
        this.value = value;
    }


    /**
     * Instantiates a new instruction.
     * 
     * @param type the type
     * @param target the target
     * @param value the value
     * @param errorMessage the error message
     */
    public Instruction(final SeleniumActionType type, final String target, final String value, final String errorMessage) {
        super();
        this.type = type;
        this.target = target;
        this.value = value;
        this.errorMessage = errorMessage;
    }


    /**
     * Instantiates a new instruction.
     * 
     * @param uid the uid
     * @param type the type
     * @param target the target
     * @param value the value
     * @param errorMessage the error message
     */
    protected Instruction(final long uid, final SeleniumActionType type, final String target, final String value,
            final String errorMessage) {
        super();
        this.uid = uid;
        this.type = type;
        this.target = target;
        this.value = value;
        this.errorMessage = errorMessage;
    }


    // =========================================================================
    // OVERRIDES
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        final int nbBytes = 32;
        int result = 1;
        result = prime * result + (int) (uid ^ (uid >>> nbBytes));
        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (this == obj) {
            result = true;
        }

        if (obj != null && getClass() == obj.getClass()) {
            final Instruction other = (Instruction) obj;
            result = uid == other.uid;
        }

        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Instruction [");

        result.append("uid=" + uid);
        result.append(", type=" + type);
        result.append(", target=" + target);
        result.append(", value=" + value);
        result.append(", errorMessage=" + errorMessage);

        result.append("]");
        return result.toString();
    }


    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================


    /**
     * Gets the uid.
     * 
     * @return the uid
     */
    public long getUid() {
        return uid;
    }


    /**
     * Sets the uid.
     * 
     * @param uid the new uid
     */
    public void setUid(long uid) {
        this.uid = uid;
    }


    /**
     * Gets the type.
     * 
     * @return the type
     */
    public SeleniumActionType getType() {
        return type;
    }


    /**
     * Sets the type.
     * 
     * @param type the new type
     */
    public void setType(SeleniumActionType type) {
        this.type = type;
    }

    /**
     * Gets the target.
     * 
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the target.
     * 
     * @param target the new target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     * 
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the error message.
     * 
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * 
     * @param errorMessage the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
