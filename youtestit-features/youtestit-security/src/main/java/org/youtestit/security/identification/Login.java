package org.youtestit.security.identification;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.YoutestitMSG;
import org.youtestit.commons.utils.sha1.Sha1Encryption;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.User;

@Stateful
@Named
public class Login extends BaseAuthenticator implements Authenticator {

    @Inject
    private Logger      log;

    @Inject
    private UserDAO     userDAO;

    @Inject
    private Messages    messages;

    @Inject
    private Credentials credentials;

    @Inject
    Identity            identity;

    @Inject
    @Authenticated
    private Event<User> loginEventSrc;

    @Override
    public void authenticate() {
        log.info("SeamAuthenticator.authenticate()");

        try {
            authenticateJPA();

        } catch (ClientException e) {
            log.error(e);
        }
    }


    /**
     * Authenticate jpa.
     * 
     * @throws ClientException the client exception
     */
    protected void authenticateJPA() throws ClientException {
        User user = userDAO.getUserByLogin(credentials.getUsername());
        boolean hasNoError = true;
        
        hasNoError = authorizeUser(user);

        String password = null;
        if (hasNoError) {
            if (credentials != null && credentials.getCredential() instanceof PasswordCredential) {
                password = ((PasswordCredential) credentials.getCredential()).getValue();
            }
            if (password == null) {
                messages.error(new YoutestitMSG("error.login.password.require"));
                hasNoError = false;
            }
        }

        if (hasNoError) {
            final String cryptedPassword = Sha1Encryption.getInstance().encryptToSha1(password);

            if (user.getPassword().equals(cryptedPassword)) {
                loginEventSrc.fire(user);
                setUser(new SimpleUser(user.getLogin()));
                identity.getUser();
            } else {
                messages.error(new YoutestitMSG("error.login.password.wrong"));
                hasNoError = false;
            }
        }

        if (hasNoError) {
            setStatus(AuthenticationStatus.SUCCESS);
        } else {
            setStatus(AuthenticationStatus.FAILURE);
        }
    }

    /**
     * Check if user is authorized.
     * 
     * @param user the user to check
     * @return true, if successful
     */
    protected boolean authorizeUser(User user) {
        boolean authorize = true;
        if (user == null) {
            messages.error(new YoutestitMSG("error.login.user.not.exists"));
            authorize = false;
        } else if (!user.isEnable()) {
            messages.error(new YoutestitMSG("error.login.user.not.enable"));
            authorize = false;
        } else if (user.getProfile() != null && !user.getProfile().isEnable()) {
            messages.error(new YoutestitMSG("error.login.profile.not.enable"));
            authorize = false;
        }
        return authorize;
    }
}
