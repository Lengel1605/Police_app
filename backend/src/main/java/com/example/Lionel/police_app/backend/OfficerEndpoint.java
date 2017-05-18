package com.example.Lionel.police_app.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "officerApi",
        version = "v1",
        resource = "officer",
        namespace = @ApiNamespace(
                ownerDomain = "backend.police_app.Lionel.example.com",
                ownerName = "backend.police_app.Lionel.example.com",
                packagePath = ""
        )
)
public class OfficerEndpoint {

    private static final Logger logger = Logger.getLogger(OfficerEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Officer.class);
    }

    /**
     * Returns the {@link Officer} with the corresponding ID.
     *
     * @param idOfficer the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Officer} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "officer/{idOfficer}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Officer get(@Named("idOfficer") long idOfficer) throws NotFoundException {
        logger.info("Getting Officer with ID: " + idOfficer);
        Officer officer = ofy().load().type(Officer.class).id(idOfficer).now();
        if (officer == null) {
            throw new NotFoundException("Could not find Officer with ID: " + idOfficer);
        }
        return officer;
    }

    /**
     * Inserts a new {@code Officer}.
     */
    @ApiMethod(
            name = "insert",
            path = "officer",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Officer insert(Officer officer) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that officer.idOfficer has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(officer).now();
        logger.info("Created Officer with ID: " + officer.getIdOfficer());

        return ofy().load().entity(officer).now();
    }

    /**
     * Updates an existing {@code Officer}.
     *
     * @param idOfficer the ID of the entity to be updated
     * @param officer   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code idOfficer} does not correspond to an existing
     *                           {@code Officer}
     */
    @ApiMethod(
            name = "update",
            path = "officer/{idOfficer}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Officer update(@Named("idOfficer") long idOfficer, Officer officer) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(idOfficer);
        ofy().save().entity(officer).now();
        logger.info("Updated Officer: " + officer);
        return ofy().load().entity(officer).now();
    }

    /**
     * Deletes the specified {@code Officer}.
     *
     * @param idOfficer the ID of the entity to delete
     * @throws NotFoundException if the {@code idOfficer} does not correspond to an existing
     *                           {@code Officer}
     */
    @ApiMethod(
            name = "remove",
            path = "officer/{idOfficer}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("idOfficer") long idOfficer) throws NotFoundException {
        checkExists(idOfficer);
        ofy().delete().type(Officer.class).id(idOfficer).now();
        logger.info("Deleted Officer with ID: " + idOfficer);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "officer",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Officer> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Officer> query = ofy().load().type(Officer.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Officer> queryIterator = query.iterator();
        List<Officer> officerList = new ArrayList<Officer>(limit);
        while (queryIterator.hasNext()) {
            officerList.add(queryIterator.next());
        }
        return CollectionResponse.<Officer>builder().setItems(officerList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long idOfficer) throws NotFoundException {
        try {
            ofy().load().type(Officer.class).id(idOfficer).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Officer with ID: " + idOfficer);
        }
    }
}