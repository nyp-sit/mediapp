package com.example.supertest;

import com.example.supertest.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "signupendpoint", namespace = @ApiNamespace(ownerDomain = "example.com", ownerName = "example.com", packagePath = "supertest"))
public class SignUpEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listSignUp")
	public CollectionResponse<SignUp> listSignUp(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<SignUp> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from SignUp as SignUp");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<SignUp>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (SignUp obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<SignUp> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getSignUp")
	public SignUp getSignUp(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		SignUp signup = null;
		try {
			signup = mgr.find(SignUp.class, id);
		} finally {
			mgr.close();
		}
		return signup;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param signup the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertSignUp")
	public SignUp insertSignUp(SignUp signup) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsSignUp(signup)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(signup);
		} finally {
			mgr.close();
		}
		return signup;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param signup the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateSignUp")
	public SignUp updateSignUp(SignUp signup) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsSignUp(signup)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(signup);
		} finally {
			mgr.close();
		}
		return signup;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeSignUp")
	public void removeSignUp(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			SignUp signup = mgr.find(SignUp.class, id);
			mgr.remove(signup);
		} finally {
			mgr.close();
		}
	}

	private boolean containsSignUp(SignUp signup) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			SignUp item = mgr.find(SignUp.class, signup.getKey());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
