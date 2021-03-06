/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stroom.security.shared;

import stroom.entity.shared.BaseEntity;
import stroom.entity.shared.DocRef;
import stroom.entity.shared.HasUuid;
import stroom.util.shared.EqualsBuilder;
import stroom.util.shared.HasDisplayValue;
import stroom.util.shared.HashCodeBuilder;
import stroom.util.shared.SharedObject;
import stroom.util.shared.ToStringBuilder;

public class UserRef extends DocRef {
    private static final long serialVersionUID = 5883121212911541301L;

    private boolean group;

    public UserRef() {
        // Default constructor necessary for GWT serialisation.
    }

    public static UserRef create(final User user) {
        if (user == null) {
            return null;
        }

        final String type = user.getType();
        final String uuid = user.getUuid();
        final String name = user.getName();

        return new UserRef(type, uuid, name, user.isGroup());
    }


    public UserRef(final String type, final String uuid, final String name, final boolean group) {
        super(type, null, uuid, name);
        this.group = group;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(final boolean group) {
        this.group = group;
    }
//
//    @Override
//    public boolean equals(final Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof UserRef)) {
//            return false;
//        }
//
//        final UserRef userRef = (UserRef) o;
//        final EqualsBuilder builder = new EqualsBuilder();
//        builder.append(id, userRef.id);
//        builder.append(name, userRef.name);
//        builder.append(group, userRef.group);
//        return builder.isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        final HashCodeBuilder builder = new HashCodeBuilder();
//        builder.append(id);
//        builder.append(name);
//        builder.append(group);
//        return builder.toHashCode();
//    }
//
//    @Override
//    public String toString() {
//        final ToStringBuilder builder = new ToStringBuilder();
//        builder.append("id", id);
//        builder.append("group", group);
//        return builder.toString();
//    }
//
//    @Override
//    public int compareTo(UserRef o) {
//        return name.compareTo(o.name);
//    }
}
