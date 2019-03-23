package thiagodnf.doupr.core.refactoring.condition;

import thiagodnf.doupr.core.base.ElementObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.ProjectObject;

public class HasVisibilityCondition extends Condition {

    protected ElementObject element;

    protected Visibility visibility;

    public HasVisibilityCondition(ElementObject element, Visibility visibility) {
        this.element = element;
        this.visibility = visibility;
    }

    @Override
    public boolean verify(ProjectObject project) {

        if (element.getVisibility() == visibility) {
            return true;
        }

        return false;
    }

    @Override
    public String getErrorMessage() {
        return "The element " + element.getName() + " is not " + visibility;
    }
}
