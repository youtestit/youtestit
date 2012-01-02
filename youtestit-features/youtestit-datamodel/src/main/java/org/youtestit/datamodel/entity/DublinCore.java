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
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Dublin core, generic meta-data on documents conform to Dublin core standard 
 * ISO 15836:2003
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 30, 2011
 */
@Entity
public class DublinCore implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6251772001497647256L;

    /** The name. */
    @NotEmpty
    @Size(max = 64)
    @Column(length = 64, nullable = false)
    @Id
    private String            name;

    /** The title*/
    @Size(max = 128)
    @Column(length = 128)
    private String            title;

    /** The type*/
    @Enumerated(EnumType.STRING)
    private DocumentType      type;

    /** The path to document */
    @Pattern(regexp = "^([/\\w\\d_-]+)([\\w\\d]+)$")
    @NotEmpty
    private String            path;

    /** The subject. */
    @Size(max = 512)
    @Column(length = 512)
    private String            subject;

    /** The description. */
    private String            description;

    /** The children. */
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<DublinCore>  children;

    /**
     * The creator.
     * 
     * @see org.youtestit.datamodel.entity.User
     */
    @ManyToOne(optional = true, cascade = CascadeType.REMOVE)
    private User              creator;

    /** The date creation. */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar          dateCreation;

    /** The date last modify. */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar          dateLastModify;

    /** The date publish. */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar          datePublish;

    /** The language. */
    @Size(max = 32)
    @Column(length = 32)
    private String            language;

    /** The rights. */
    private String            rights;

    /** The coverage. */
    @Size(max = 256)
    @Column(length = 256)
    private String            coverage;


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
     * Instantiates a new dublin core with require values.
     * 
     * @param name the document name
     * @param path the document path
     */
    public DublinCore(final String name, final String path) {
        super();
        this.name = name;
        this.path = path;
    }

    /**
     * Instantiates a new dublin core.
     * 
     * @param name the document name
     * @param title the document title
     * @param path the document path (like /foo/bar )
     * @param subject the document subject, it's document short description
     * @param creator the document creator
     * @param dateCreation the document name
     * 
     * @see org.youtestit.datamodel.entity.User
     */
    public DublinCore(final String name, final String title, final String path, final String subject,
            final User creator, final Calendar dateCreation) {
        super();
        this.name = name;
        this.title = title;
        this.path = path;
        this.subject = subject;
        this.creator = creator;
        this.dateCreation = dateCreation;
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

        int nameHash = 0;
        if (name != null) {
            nameHash = name.hashCode();
        }

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
            result = same(name, other.name) && same(path, other.path);
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
        StringBuilder result = new StringBuilder(this.getName());
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
    protected String toStringContent(){
        StringBuilder result = new StringBuilder();
        final String nullObj = "null";
        
        result.append("name=");
        result.append("name");
        
        result.append(", title=");
        result.append(title);
        
        result.append(", type=");
        result.append(type);
        
        result.append(", path=");
        result.append(path);
        
        result.append(", subject=");
        result.append(subject);
        
        result.append(", description=");
        result.append(description);
        
        result.append(", children=");
        if(children==null){
            result.append(nullObj);
        }else{
            result.append("{");
            for(DublinCore item: children){
                result.append(item);
                result.append(",");
            }
            result.append("}");
        }
        
        
        result.append(", creator=");
        if(creator==null){
            result.append(nullObj);
        }else{
            result.append(creator.getLogin());    
        }
        
        
        result.append(", dateCreation=");
        result.append(dateCreation);
        
        result.append(", dateLastModify=");
        result.append(dateLastModify);
        
        result.append(", datePublish=");
        result.append(datePublish);
        
        
        result.append(", language=");
        result.append(language);
        
        result.append(", rights=");
        result.append(rights);
        
        result.append(", coverage=");
        result.append(coverage);
        
        return result.toString();
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
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

}
