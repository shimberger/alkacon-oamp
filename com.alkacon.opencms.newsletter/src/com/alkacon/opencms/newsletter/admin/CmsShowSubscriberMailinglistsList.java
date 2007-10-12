/*
 * File   : $Source: /alkacon/cvs/alkacon/com.alkacon.opencms.newsletter/src/com/alkacon/opencms/newsletter/admin/CmsShowSubscriberMailinglistsList.java,v $
 * Date   : $Date: 2007/10/12 15:19:08 $
 * Version: $Revision: 1.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (c) 2005 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.alkacon.opencms.newsletter.admin;

import org.opencms.jsp.CmsJspActionElement;
import org.opencms.util.CmsStringUtil;
import org.opencms.workplace.list.CmsListColumnDefinition;
import org.opencms.workplace.list.CmsListMetadata;
import org.opencms.workplace.list.I_CmsListDirectAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * Subscriber mailing lists overview view.<p>
 * 
 * @author Michael Moossen  
 * 
 * @version $Revision: 1.2 $ 
 * 
 * @since 6.0.0 
 */
public class CmsShowSubscriberMailinglistsList extends org.opencms.workplace.tools.accounts.CmsShowUserGroupsList {

    /**
     * Public constructor.<p>
     * 
     * @param jsp an initialized JSP action element
     */
    public CmsShowSubscriberMailinglistsList(CmsJspActionElement jsp) {

        super(jsp, LIST_ID + "l");
        getList().setName(Messages.get().container(Messages.GUI_SUBSCRIBERMAILINGLISTS_LIST_NAME_0));
    }

    /**
     * Public constructor with JSP variables.<p>
     * 
     * @param context the JSP page context
     * @param req the JSP request
     * @param res the JSP response
     */
    public CmsShowSubscriberMailinglistsList(PageContext context, HttpServletRequest req, HttpServletResponse res) {

        this(new CmsJspActionElement(context, req, res));
    }

    /**
     * @see org.opencms.workplace.CmsWorkplace#initMessages()
     */
    protected void initMessages() {

        // add specific dialog resource bundle
        addMessages(Messages.get().getBundleName());
        // add default resource bundles
        super.initMessages();
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsUserGroupsList#setColumns(org.opencms.workplace.list.CmsListMetadata)
     */
    protected void setColumns(CmsListMetadata metadata) {

        super.setColumns(metadata);

        CmsListColumnDefinition iconCol = metadata.getColumnDefinition(LIST_COLUMN_ICON);
        iconCol.setName(Messages.get().container(Messages.GUI_MAILINGLISTS_LIST_COLS_ICON_0));
        iconCol.setHelpText(Messages.get().container(Messages.GUI_MAILINGLISTS_LIST_COLS_ICON_HELP_0));
        I_CmsListDirectAction dirAction = iconCol.getDirectAction(LIST_ACTION_ICON_DIRECT);
        dirAction.setName(Messages.get().container(Messages.GUI_MAILINGLISTS_LIST_DIRECT_NAME_0));
        dirAction.setHelpText(Messages.get().container(Messages.GUI_MAILINGLISTS_LIST_DIRECT_HELP_0));

        CmsListColumnDefinition displayCol = new CmsListColumnDefinition(LIST_COLUMN_DISPLAY);
        displayCol.setName(Messages.get().container(Messages.GUI_MAILINGLISTS_LIST_COLS_NAME_0));

        CmsListColumnDefinition descCol = new CmsListColumnDefinition(LIST_COLUMN_DESCRIPTION);
        descCol.setName(Messages.get().container(Messages.GUI_MAILINGLISTS_LIST_COLS_DESCRIPTION_0));
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsUserGroupsList#validateParamaters()
     */
    protected void validateParamaters() throws Exception {

        super.validateParamaters();
        // this is to prevent the switch to the root ou 
        // if the oufqn param get lost (by reloading for example)
        if (CmsStringUtil.isEmptyOrWhitespaceOnly(getParamOufqn())) {
            throw new Exception();
        }
    }
}
