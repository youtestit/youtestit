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

import static javax.persistence.FetchType.LAZY;
import static org.youtestit.commons.utils.constants.Constants.SEP;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({ @NamedQuery(name = User.ALL_USERS, query = "FROM User"),
        @NamedQuery(name = User.USER_BY_LOGIN, query = "FROM User WHERE login=:" + User.USER_BY_LOGIN_PARAM_LOGIN) })
@Table(name = "yti_user")
public class User implements Serializable {

    // =========================================================================
    // STATICS ATTRIBUTES
    // =========================================================================
    /** The Constant ALL_USERS. */
    public static final String ALL_USERS                 = "allUser";

    /** The Constant USER_BY_LOGIN. */
    public static final String USER_BY_LOGIN             = "userByLogin";

    /** The Constant USER_BY_LOGIN_PARAM_LOGIN. */
    public static final String USER_BY_LOGIN_PARAM_LOGIN = "login";

    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID          = -4060996747953916843L;


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The login. */
    @Id
    @NotNull
    private String             login;

    /** The email. */
    @Email
    @NotEmpty
    private String             email;

    /** The password. */
    @NotEmpty
    @Basic(fetch = LAZY)
    private String             password;

    /** The firstname. */
    @NotEmpty
    @Basic(fetch = LAZY)
    private String             firstname;

    /** The lastname. */
    @NotEmpty
    @Basic(fetch = LAZY)
    private String             lastname;

    /** The gravatar. */
    @Basic(fetch = LAZY)
    private String             gravatar;

    /** The phone number. */
    @Basic(fetch = LAZY)
    private String             phoneNumber;

    /** The cellular number. */
    @Basic(fetch = LAZY)
    private String             cellularNumber;

    /** The office. */
    @Basic(fetch = LAZY)
    private String             office;

    /** The description. */
    @Basic(fetch = LAZY)
    private String             description;

    /** The enable. */
    private boolean            enable                    = false;


    /**
     * The level.
     */
    @ManyToOne(optional = true, fetch = LAZY)
    private Profile            profile;


    /** The administrator. */
    @ManyToMany(fetch = LAZY, targetEntity = Group.class, mappedBy = "users")
    private List<Group>        groups;

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
     * @param profile the profile
     */
    public User(final String login, final String email, final String password, final String firstname,
            final String lastname, final Profile profile) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profile = profile;
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
    public boolean equals(final Object obj) {
        boolean result = false;

        if (this == obj) {
            return true;
        }

        if (obj != null && getClass() == obj.getClass()) {
            final User other = (User) obj;
            if (login != null && login.equals(other.login)) {
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
        StringBuilder result = new StringBuilder("User [");

        result.append("login=");
        result.append(login);
        result.append(SEP);

        result.append("email=");
        result.append(email);
        result.append(SEP);

        result.append("password=");
        result.append(password);
        result.append(SEP);

        result.append("firstname=");
        result.append(firstname);
        result.append(SEP);

        result.append("lastname=");
        result.append(lastname);
        result.append(SEP);

        result.append("gravatar=");
        result.append(gravatar);
        result.append(SEP);

        result.append("phoneNumber=");
        result.append(phoneNumber);
        result.append(SEP);

        result.append("cellularNumber=");
        result.append(cellularNumber);
        result.append(SEP);

        result.append("description=");
        result.append(description);
        result.append(SEP);

        result.append("office=");
        result.append(office);
        result.append(SEP);

        result.append("enable=");
        result.append(enable);
        result.append(SEP);
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
    public void setLogin(final String login) {
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
    public void setEmail(final String email) {
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
    public void setPassword(final String password) {
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
    public void setFirstname(final String firstname) {
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
    public void setLastname(final String lastname) {
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
    public void setGravatar(final String gravatar) {
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
    public void setPhoneNumber(final String phoneNumber) {
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
    public void setCellularNumber(final String cellularNumber) {
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
    public void setOffice(final String office) {
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
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the profile.
     * 
     * @return the profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets the profile.
     * 
     * @param profile the new profile
     */
    public void setProfile(final Profile profile) {
        this.profile = profile;
    }

    /**
     * Gets the groups.
     * 
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Sets the groups.
     * 
     * @param groups the new groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Checks if is enable.
     * 
     * @return true, if is enable
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Sets the enable.
     * 
     * @param enable the new enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }


}
