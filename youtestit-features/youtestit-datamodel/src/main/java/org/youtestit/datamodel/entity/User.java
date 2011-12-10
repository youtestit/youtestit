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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 8, 2011
 */
@Entity
@Table(name = "yti_user")
public class User {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    
    /** The login. */
    @Id
    @NotNull
    private String login;

    /** The email. */
    @Email
    @NotEmpty
    private String email;

    /** The password. */
    @NotEmpty
    private String password;

    /** The firstname. */
    @NotEmpty
    private String firstname;

    /** The lastname. */
    @NotEmpty
    private String lastname;

    /** The gravatar. */
    private String gravatar;

    /** The phone number. */
    private String phoneNumber;

    /** The cellular number. */
    private String cellularNumber;

    /** The office. */
    private String office;

    /** The description. */
    private String description;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Default constructor for instantiates a new user.
     */
    public User() {
        super();
    }

    /**
     * Constructor with all require values.
     * 
     * @param login the login
     * @param email the email
     * @param password the password
     * @param firstname the firstname
     * @param lastname the lastname
     */
    public User(String login, String email, String password, String firstname,
            String lastname) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
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
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (this == obj) {
            return true;
        }

        if (obj != null && getClass() == obj.getClass()) {
            final User other = (User) obj;
            if (login != null  && login.equals(other.login)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final String sep = ",";
        StringBuilder result = new StringBuilder("User [");

        result.append("login=");
        result.append(login);
        result.append(sep);

        result.append("email=");
        result.append(email);
        result.append(sep);

        result.append("password=");
        result.append(password);
        result.append(sep);

        result.append("firstname=");
        result.append(firstname);
        result.append(sep);

        result.append("lastname=");
        result.append(lastname);
        result.append(sep);

        result.append("gravatar=");
        result.append(gravatar);
        result.append(sep);

        result.append("phoneNumber=");
        result.append(phoneNumber);
        result.append(sep);

        result.append("cellularNumber=");
        result.append(cellularNumber);
        result.append(sep);

        result.append("description=");
        result.append(description);
        result.append("]");

        return result.toString();
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the login.
     * 
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     * 
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the email.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the firstname.
     * 
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the firstname.
     * 
     * @param firstname the new firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the lastname.
     * 
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the lastname.
     * 
     * @param lastname the new lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the gravatar.
     * 
     * @return the gravatar
     */
    public String getGravatar() {
        return gravatar;
    }

    /**
     * Sets the gravatar.
     * 
     * @param gravatar the new gravatar
     */
    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    /**
     * Gets the phone number.
     * 
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     * 
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the cellular number.
     * 
     * @return the cellular number
     */
    public String getCellularNumber() {
        return cellularNumber;
    }

    /**
     * Sets the cellular number.
     * 
     * @param cellularNumber the new cellular number
     */
    public void setCellularNumber(String cellularNumber) {
        this.cellularNumber = cellularNumber;
    }

    /**
     * Gets the office.
     * 
     * @return the office
     */
    public String getOffice() {
        return office;
    }

    /**
     * Sets the office.
     * 
     * @param office the new office
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
