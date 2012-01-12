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

import static org.youtestit.commons.utils.Constants.NULL_OBJ;
import static org.youtestit.commons.utils.Constants.ITEM_OPEN;
import static org.youtestit.commons.utils.Constants.ITEM_CLOSE;
import static org.youtestit.commons.utils.Constants.SEP;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.youtestit.datamodel.enums.DocumentType;


/**
 * Dublin core, generic meta-data on documents conform to Dublin core standard
 * ISO 15836:2003.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 30, 2011
 */
@Entity
@Table(name = "document")
public class DublinCore implements Serializable {
    // =========================================================================
    // STATICS ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID    = 6251772001497647256L;



    /** The Constant MAX_LENGTH_TITLE : 128 chars. */
    private static final int    MAX_LENGTH_TITLE    = 128;

    /** The Constant MAX_LENGTH_SUBJECT : 512 chars. */
    private static final int    MAX_LENGTH_SUBJECT  = 512;

    /** The Constant MAX_LENGTH_LANGUAGE : 32 chars. */
    private static final int    MAX_LENGTH_LANGUAGE = 32;

    /** The Constant MAX_LENGTH_COVERAGE : 256 chars. */
    private static final int    MAX_LENGTH_COVERAGE = 256;


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The name. */
    @Id
    @GeneratedValue
    private long                uid;

    /** The title. */
    @Size(max = MAX_LENGTH_TITLE)
    @Column(length = MAX_LENGTH_TITLE)
    private String              title;

    /** The type. */
    @Enumerated(EnumType.STRING)
    private DocumentType        type;

    /** The path to document. */
    @Pattern(regexp = "^([/\\w\\d_-]+)([\\w\\d]+)$")
    @NotEmpty
    private String              path;

    /** The subject. */
    @Size(max = MAX_LENGTH_SUBJECT)
    @Column(length = MAX_LENGTH_SUBJECT)
    private String              subject;

    /** The description. */
    private String              description;

    /** The children. */
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<DublinCore>    children;

    /**
     * The creator.
     * 
     * @see org.youtestit.datamodel.entity.User
     */
    @ManyToOne(optional = true, cascade = CascadeType.REMOVE)
    private User                creator;

    /** The date creation. */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar            dateCreation;

    /** The date last modify. */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar            dateLastModify;

    /** The date publish. */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar            datePublish;

    /** The language. */
    @Size(max = MAX_LENGTH_LANGUAGE)
    @Column(length = MAX_LENGTH_LANGUAGE)
    private String              language;

    /** The rights. */
    private String              rights;

    /** The coverage. */
    @Size(max = MAX_LENGTH_COVERAGE)
    @Column(length = MAX_LENGTH_COVERAGE)
    private String              coverage;

    /** The tags. */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class, cascade = CascadeType.ALL)
    @JoinTable(name = "tags_documents")
    private List<Tag>           tags;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new dublin core.
     */
    public DublinCore() {
        super();
        this.type = DocumentType.DUBLIN_CORE;
    }

    /**
     * Instantiates a new dublin core for unit test.
     * 
     * @param uid the uid
     * @param title the title
     * @param path the path
     */
    protected DublinCore(final long uid, final String title, final String path) {
        super();
        this.uid = uid;
        this.title = title;
        this.path = path;
    }


    /**
     * Instantiates a new dublin core.
     * 
     * @param title the title
     * @param path the path
     */
    public DublinCore(final String title, final String path) {
        super();
        this.title = title;
        this.path = path;
    }

    /**
     * Instantiates a new dublin core.
     * 
     * @param title the document title
     * @param path the document path (like /foo/bar )
     * @param subject the document subject, it's document short description
     * @param creator the document creator
     * @param dateCreation the document name
     * @see org.youtestit.datamodel.entity.User
     */
    public DublinCore(final String title, final String path, final String subject, final User creator,
            final Calendar dateCreation) {
        super();
        this.title = title;
        this.path = path;
        this.subject = subject;
        this.creator = creator;
        this.dateCreation = dateCreation;
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to adds a child.
     * 
     * @param child the child to add
     */
    public void addChild(final DublinCore child) {
        if (child != null) {
            if (children == null) {
                children = new ArrayList<DublinCore>();
            }
            children.add(child);
        }
    }


    /**
     * Allow to removes a child.
     * 
     * @param child the child to remove
     */
    public void removeChild(final DublinCore child) {
        if (children != null && child != null && children.contains(child)) {
            children.remove(child);
        }
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

        final int nameHash = String.valueOf(uid).hashCode();

        int pathHash = 0;
        if (path != null) {
            pathHash = path.hashCode();
        }

        result = prime * result + nameHash;
        result = prime * result + pathHash;
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

        if (obj == null || getClass() != obj.getClass()) {
            result = false;
        } else {
            final DublinCore other = (DublinCore) obj;
            result = (uid == other.uid) && same(path, other.path);
        }

        return result;
    }

    /**
     * Allox to check if two object is equals.
     * 
     * @param objA first simple object referer
     * @param objb second object to test
     * @return true, if equals
     */
    protected boolean same(Object objA, Object objb) {
        boolean result = false;
        if (objA == null) {
            result = objb == null;
        } else {
            result = objA.equals(objb);
        }
        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder(getClass().getName());
        result.append("[");
        result.append(toStringContent());
        result.append("]");

        return result.toString();
    }

    /**
     * allow to contribute parent toString content.
     * 
     * @return the string
     */
    protected String toStringContent() {
        final StringBuilder result = new StringBuilder();

        result.append("uid=" + uid);
        result.append(", title=" + title);
        result.append(", type=" + type);
        result.append(", path=" + path);
        result.append(", subject=" + subject);
        result.append(", description=" + description);

        result.append(creatorToString());
        result.append(childrenToString());

        result.append(", dateCreation=" + dateFormat(dateCreation));
        result.append(", dateLastModify=" + dateFormat(dateLastModify));
        result.append(", datePublish=" + dateFormat(datePublish));

        result.append(", language=" + language);
        result.append(", rights=" + rights);
        result.append(", coverage=" + coverage);

        result.append(tagsToString());

        return result.toString();
    }


    /**
     * Allow to format Calendar.
     * 
     * @param calendar the calendar to format
     * @return calendar string representation
     */
    protected String dateFormat(Calendar calendar) {
        String result = null;
        if (calendar == null) {
            result = NULL_OBJ;
        } else {
            final SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
            result = formater.format(calendar.getTime());
        }
        return result;
    }

    /**
     * allow to format creator to string.
     * 
     * @return the string
     */
    private String creatorToString() {
        final StringBuilder result = new StringBuilder();
        result.append(", creator=");
        if (creator == null) {
            result.append(NULL_OBJ);
        } else {
            result.append(creator.getLogin());
        }
        return result.toString();
    }


    /**
     * allow to format children to string.
     * 
     * @return the string
     */
    private String childrenToString() {
        final StringBuilder result = new StringBuilder();
        result.append(", children=");
        if (children == null) {
            result.append(NULL_OBJ);
        } else {
            result.append(ITEM_OPEN);
            for (DublinCore item : children) {
                result.append(item.getUid());
                result.append("@");
                result.append(item.getTitle());
                result.append(SEP);
            }
            result.append(ITEM_CLOSE);
        }
        return result.toString();
    }

    /**
     * allow to format tags to string.
     * 
     * @return the string
     */
    private String tagsToString() {
        final StringBuilder result = new StringBuilder();
        result.append(", tags=");
        if (tags == null) {
            result.append(NULL_OBJ);
        } else {
            result.append(ITEM_OPEN);
            for (Tag item : tags) {
                result.append(item.getName());
                result.append(SEP);
            }
            result.append(ITEM_CLOSE);
        }
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
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the type.
     * 
     * @return the type
     */
    public DocumentType getType() {
        return type;
    }

    /**
     * Sets the type.
     * 
     * @param type the new type
     */
    public void setType(DocumentType type) {
        this.type = type;
    }

    /**
     * Gets the path.
     * 
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     * 
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the subject.
     * 
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     * 
     * @param subject the new subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
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

    /**
     * Gets the children.
     * 
     * @return the children
     */
    public List<DublinCore> getChildren() {
        return children;
    }

    /**
     * Sets the children.
     * 
     * @param children the new children
     */
    public void setChildren(List<DublinCore> children) {
        this.children = children;
    }

    /**
     * Gets the creator.
     * 
     * @return the creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Sets the creator.
     * 
     * @param creator the new creator
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Gets the date creation.
     * 
     * @return the date creation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Sets the date creation.
     * 
     * @param dateCreation the new date creation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Gets the date last modify.
     * 
     * @return the date last modify
     */
    public Calendar getDateLastModify() {
        return dateLastModify;
    }

    /**
     * Sets the date last modify.
     * 
     * @param dateLastModify the new date last modify
     */
    public void setDateLastModify(Calendar dateLastModify) {
        this.dateLastModify = dateLastModify;
    }

    /**
     * Gets the date publish.
     * 
     * @return the date publish
     */
    public Calendar getDatePublish() {
        return datePublish;
    }

    /**
     * Sets the date publish.
     * 
     * @param datePublish the new date publish
     */
    public void setDatePublish(Calendar datePublish) {
        this.datePublish = datePublish;
    }

    /**
     * Gets the language.
     * 
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     * 
     * @param language the new language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the rights.
     * 
     * @return the rights
     */
    public String getRights() {
        return rights;
    }

    /**
     * Sets the rights.
     * 
     * @param rights the new rights
     */
    public void setRights(String rights) {
        this.rights = rights;
    }

    /**
     * Gets the coverage.
     * 
     * @return the coverage
     */
    public String getCoverage() {
        return coverage;
    }

    /**
     * Sets the coverage.
     * 
     * @param coverage the new coverage
     */
    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    /**
     * Gets the tags.
     * 
     * @return the tags
     */
    public List<Tag> getTags() {
        if (this.tags == null) {
            this.tags = new ArrayList<Tag>();
        }
        return tags;
    }

    /**
     * Sets the tags.
     * 
     * @param tags the new tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Adds a tag.
     * 
     * @param tag the tag to add
     */
    public void addTag(final Tag tag) {
        if (tag != null) {
            if (tags == null) {
                tags = new ArrayList<Tag>();
            }
            tags.add(tag);
        }
    }

    /**
     * Allow to delete a tag.
     * 
     * @param tag the tag to delete
     */
    public void removeTag(final Tag tag) {
        if (tag != null && tags != null && tags.contains(tag)) {
            tags.remove(tag);
        }
    }

    /**
     * Gets the null obj.
     *
     * @return the null obj
     */
    protected static String getNullObj() {
        return NULL_OBJ;
    }

    /**
     * Gets the sep.
     *
     * @return the sep
     */
    protected static String getSep() {
        return SEP;
    }

    /**
     * Gets the item open.
     *
     * @return the item open
     */
    protected static String getItemOpen() {
        return ITEM_OPEN;
    }

    /**
     * Gets the item close.
     *
     * @return the item close
     */
    protected static String getItemClose() {
        return ITEM_CLOSE;
    }

}
