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

package org.opennms.netmgt.flows.elastic.ext;

import java.math.BigInteger;

import org.opennms.core.utils.InetAddressUtils;
import org.opennms.netmgt.flows.elastic.ConversationKeyUtils;
import org.opennms.netmgt.flows.elastic.FlowDocument;

public class ConversationClassifierImpl implements ConversationClassifier {
    @Override
    public ConversationClassification classify(FlowDocument document) {
        final ConversationClassificationImpl classification = new ConversationClassificationImpl();

        final boolean isInitiator = isInitiator(document);
        classification.setInitiator(isInitiator);
        classification.setConversationKey(ConversationKeyUtils.keyFor(document, isInitiator));
        return classification;
    }

    // Determine if the provided flow is the initiator.
    // Yes, this may not be 100% accurate, but is a very easy way of defining the direction of the flow in most cases.
    protected static boolean isInitiator(FlowDocument document) {
        if (document.getSrcPort()  > document.getDstPort()) {
            return true;
        } else if (document.getSrcPort() == document.getDstPort()) {
            // Tie breaker
            final BigInteger sourceAddressAsInt = InetAddressUtils.toInteger(InetAddressUtils.addr(document.getSrcAddr()));
            final BigInteger destAddressAsInt = InetAddressUtils.toInteger(InetAddressUtils.addr(document.getDstAddr()));
            return sourceAddressAsInt.compareTo(destAddressAsInt) > 0;
        }
        return false;
    }
}
