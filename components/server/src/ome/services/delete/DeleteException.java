/*
 *   $Id$
 *
 *   Copyright 2010 Glencoe Software, Inc. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.services.delete;

import ome.api.IDelete;
import ome.services.graphs.GraphException;

/**
 * Exception which will be thrown by activities within {@link DeleteSpec}
 * implementations. This is a caught exception so that internal API consumers
 * will have to handle any issues any create the proper remote exceptions for
 * clients.
 *
 * @author Josh Moore, josh at glencoesoftware.com
 * @since Beta4.2.1
 * @see IDelete
 */
public class DeleteException extends GraphException {

    private static final long serialVersionUID = -4619031026063199194L;

    public DeleteException(String msg) {
        super(msg);
    }

}
