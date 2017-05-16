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
        name = "interventionApi",
        version = "v1",
        resource = "intervention",
        namespace = @ApiNamespace(
                ownerDomain = "Constructors.backend.police_app.Lionel.example.com",
                ownerName = "Constructors.backend.police_app.Lionel.example.com",
                packagePath = ""
        )
)
public class InterventionEndpoint {

    private static final Logger logger = Logger.getLogger(InterventionEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Intervention.class);
    }

    /**
     * Returns the {@link Intervention} with the corresponding ID.
     *
     * @param idIntervention the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Intervention} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "intervention/{idIntervention}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Intervention get(@Named("idIntervention") long idIntervention) throws NotFoundException {
        logger.info("Getting Intervention with ID: " + idIntervention);
        Intervention intervention = ofy().load().type(Intervention.class).id(idIntervention).now();
        if (intervention == null) {
            throw new NotFoundException("Could not find Intervention with ID: " + idIntervention);
        }
        return intervention;
    }

    /**
     * Inserts a new {@code Intervention}.
     */
    @ApiMethod(
            name = "insert",
            path = "intervention",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Intervention insert(Intervention intervention) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that intervention.idIntervention has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(intervention).now();
        logger.info("Created Intervention with ID: " + intervention.getIdIntervention());

        return ofy().load().entity(intervention).now();
    }

    /**
     * Updates an existing {@code Intervention}.
     *
     * @param idIntervention the ID of the entity to be updated
     * @param intervention   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code idIntervention} does not correspond to an existing
     *                           {@code Intervention}
     */
    @ApiMethod(
            name = "update",
            path = "intervention/{idIntervention}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Intervention update(@Named("idIntervention") long idIntervention, Intervention intervention) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(idIntervention);
        ofy().save().entity(intervention).now();
        logger.info("Updated Intervention: " + intervention);
        return ofy().load().entity(intervention).now();
    }

    /**
     * Deletes the specified {@code Intervention}.
     *
     * @param idIntervention the ID of the entity to delete
     * @throws NotFoundException if the {@code idIntervention} does not correspond to an existing
     *                           {@code Intervention}
     */
    @ApiMethod(
            name = "remove",
            path = "intervention/{idIntervention}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("idIntervention") long idIntervention) throws NotFoundException {
        checkExists(idIntervention);
        ofy().delete().type(Intervention.class).id(idIntervention).now();
        logger.info("Deleted Intervention with ID: " + idIntervention);
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
            path = "intervention",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Intervention> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Intervention> query = ofy().load().type(Intervention.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Intervention> queryIterator = query.iterator();
        List<Intervention> interventionList = new ArrayList<Intervention>(limit);
        while (queryIterator.hasNext()) {
            interventionList.add(queryIterator.next());
        }
        return CollectionResponse.<Intervention>builder().setItems(interventionList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long idIntervention) throws NotFoundException {
        try {
            ofy().load().type(Intervention.class).id(idIntervention).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Intervention with ID: " + idIntervention);
        }
    }
}