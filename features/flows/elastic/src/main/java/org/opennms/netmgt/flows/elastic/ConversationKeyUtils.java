/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2017 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2017 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.flows.elastic;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opennms.netmgt.flows.model.ConversationKey;

public class ConversationKeyUtils {

    public static String egressKeyFor(FlowDocument document) {
        return new ConversationKey(document.getProtocol(),
                document.getSrcAddr(), document.getSrcPort(),
                document.getDstAddr(), document.getDstPort()).toKeyword();
    }

    public static String ingressKeyFor(FlowDocument document) {
        return new ConversationKey(document.getProtocol(),
                document.getDstAddr(), document.getDstPort(),
                document.getSrcAddr(), document.getSrcPort()).toKeyword();
    }

    public static ConversationKey keyFor(FlowDocument document, boolean isInitiator) {
        if (isInitiator) {
            return new ConversationKey(document.getProtocol(),
                    document.getSrcAddr(), document.getSrcPort(),
                    document.getDstAddr(), document.getDstPort());
        } else {
            return new ConversationKey(document.getProtocol(),
                    document.getDstAddr(), document.getDstPort(),
                    document.getSrcAddr(), document.getSrcPort());
        }
    }
}
